package eterea.core.service.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import eterea.core.service.kotlin.model.MonedaCotizacion;
import eterea.core.service.repository.MonedaCotizacionRepository;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.NavigableMap;
import java.util.TreeMap;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class MonedaCotizacionService {

    private final MonedaCotizacionRepository repository;
    private final MonedaService monedaService;
    private final WebClient webClient;
    
    private static final String BCRA_API_URL = "https://api.bcra.gob.ar/estadisticascambiarias/v1.0";

    public MonedaCotizacionService(
            MonedaCotizacionRepository repository, 
            MonedaService monedaService,
            WebClient.Builder webClientBuilder) throws Exception {
        this.repository = repository;
        this.monedaService = monedaService;
        this.webClient = createInsecureWebClient(webClientBuilder);
    }

    public List<MonedaCotizacion> findAllPeriodoCotizacion(Integer monedaId, OffsetDateTime fechaDesde, OffsetDateTime fechaHasta) {
        return repository.findAllByMonedaIdAndFechaBetween(monedaId, fechaDesde, fechaHasta);
    }

    private WebClient createInsecureWebClient(WebClient.Builder webClientBuilder) throws Exception {
        SslContext sslContext = SslContextBuilder
                .forClient()
                .trustManager(InsecureTrustManagerFactory.INSTANCE)
                .build();

        HttpClient httpClient = HttpClient.create()
                .secure(t -> t.sslContext(sslContext));

        return webClientBuilder
                .baseUrl(BCRA_API_URL)
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();
    }

    @Transactional
    public List<MonedaCotizacion> fillCotizacion(
            Integer monedaId, 
            OffsetDateTime fechaDesde, 
            OffsetDateTime fechaHasta) {
            
        // Caso especial para monedaId = 1 (peso argentino)
        if (monedaId == 1) {
            return fillPesoCotizacion(monedaId, fechaDesde, fechaHasta);
        }

        var moneda = monedaService.findByMonedaId(monedaId);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        
        // Consultar 7 días antes para tener cotizaciones anteriores
        OffsetDateTime fechaConsultaDesde = fechaDesde.minusDays(7);
        
        ApiResponse response = webClient.get()
            .uri(uriBuilder -> uriBuilder
                .path("/Cotizaciones/{moneda}")
                .queryParam("fechadesde", fechaConsultaDesde.format(formatter))
                .queryParam("fechahasta", fechaHasta.format(formatter))
                .build(moneda.getSimbolo()))
            .retrieve()
            .bodyToMono(ApiResponse.class)
            .block();

        if (response == null || response.results == null) {
            return new ArrayList<>();
        }

        // Crear un mapa ordenado con todas las cotizaciones disponibles
        NavigableMap<LocalDate, Double> cotizacionesPorFecha = new TreeMap<>();
        for (ResultadoCotizacion resultado : response.results) {
            DetalleCotizacion detalle = resultado.detalle.getFirst();
            logDetalleCotizacion(detalle);
            LocalDate fecha = LocalDate.parse(resultado.fecha);
            var cotizacion = detalle.tipoCotizacion;
            log.debug("Cotizacion al {} -> 1.00 ARS = {} {} / 1.00 {} = {} ARS", 
                fecha, 1 / cotizacion, moneda.getSimbolo(), moneda.getSimbolo(), cotizacion);
            cotizacionesPorFecha.put(fecha, cotizacion);
        }

        List<MonedaCotizacion> cotizacionesNuevas = new ArrayList<>();
        LocalDate fechaDesdeLocal = fechaDesde.toLocalDate();
        LocalDate fechaHastaLocal = fechaHasta.toLocalDate();
        
        // Iterar por cada fecha en el rango solicitado
        LocalDate currentDate = fechaDesdeLocal;
        while (!currentDate.isAfter(fechaHastaLocal)) {
            OffsetDateTime fechaActual = currentDate.atStartOfDay().atOffset(ZoneOffset.UTC);
            
            // Buscar la cotización para la fecha actual o la anterior más cercana
            Map.Entry<LocalDate, Double> cotizacionEntry = cotizacionesPorFecha.floorEntry(currentDate);
            
            if (cotizacionEntry != null) {
                // Determinar si es una cotización copiada
                byte esCopia = (byte) (cotizacionEntry.getKey().equals(currentDate) ? 0 : 1);
                Double nuevaCotizacion = cotizacionEntry.getValue();
                
                // Buscar si existe una cotización para esta fecha
                Optional<MonedaCotizacion> cotizacionExistente = repository
                    .findByMonedaIdAndFecha(monedaId, fechaActual);
                
                if (cotizacionExistente.isPresent()) {
                    MonedaCotizacion existente = cotizacionExistente.get();
                    // Actualizar solo si el valor es diferente
                    if (!existente.getCotizacion().equals(nuevaCotizacion)) {
                        existente.setCotizacion(nuevaCotizacion);
                        existente.setCopia(esCopia);
                        cotizacionesNuevas.add(existente);
                        log.debug("Actualizando cotización para {} de {} a {}", 
                            fechaActual, existente.getCotizacion(), nuevaCotizacion);
                    }
                } else {
                    // Crear nueva cotización
                    MonedaCotizacion cotizacion = new MonedaCotizacion(
                        null,
                        monedaId,
                        fechaActual,
                        nuevaCotizacion,
                        esCopia
                    );
                    cotizacionesNuevas.add(cotizacion);
                    log.debug("Creando nueva cotización para {} con valor {}", 
                        fechaActual, nuevaCotizacion);
                }
            }
            
            currentDate = currentDate.plusDays(1);
        }

        return repository.saveAll(cotizacionesNuevas);
    }

    private void logDetalleCotizacion(DetalleCotizacion detalle) {
        try {
            log.debug("Detalle cotizacion: {}", JsonMapper.builder().findAndAddModules().build().writerWithDefaultPrettyPrinter().writeValueAsString(detalle));
        } catch (JsonProcessingException e) {
            log.debug("Detalle cotizacion jsonify error: {}", e.getMessage());
        }
    }

    private List<MonedaCotizacion> fillPesoCotizacion(
            Integer monedaId,
            OffsetDateTime fechaDesde,
            OffsetDateTime fechaHasta) {

        List<MonedaCotizacion> cotizacionesNuevas = new ArrayList<>();
        LocalDate fechaDesdeLocal = fechaDesde.toLocalDate();
        LocalDate fechaHastaLocal = fechaHasta.toLocalDate();
        
        LocalDate currentDate = fechaDesdeLocal;
        while (!currentDate.isAfter(fechaHastaLocal)) {
            OffsetDateTime fechaActual = currentDate.atStartOfDay().atOffset(ZoneOffset.UTC);
            
            if (!repository.existsByMonedaIdAndFecha(monedaId, fechaActual)) {
                MonedaCotizacion cotizacion = new MonedaCotizacion(
                    null,
                    monedaId,
                    fechaActual,
                    1.0,  // Cotización siempre 1
                    (byte) 0         // No es copia
                );
                
                cotizacionesNuevas.add(cotizacion);
            }
            
            currentDate = currentDate.plusDays(1);
        }

        return repository.saveAll(cotizacionesNuevas);
    }

    // Clases para mapear la respuesta de la API
    private record ApiResponse(
        Integer status,
        MetadataResponse metadata,
        List<ResultadoCotizacion> results
    ) {}
    
    private record MetadataResponse(
        ResultSetInfo resultset
    ) {}
    
    private record ResultSetInfo(
        Integer count,
        Integer offset,
        Integer limit
    ) {}
    
    private record ResultadoCotizacion(
        String fecha,
        List<DetalleCotizacion> detalle
    ) {}
    
    private record DetalleCotizacion(
        String codigoMoneda,
        String descripcion,
        Double tipoPase,
        Double tipoCotizacion
    ) {}
}
