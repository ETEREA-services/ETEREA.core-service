package eterea.core.service.service;

import eterea.core.service.kotlin.model.MonedaCotizacion;
import eterea.core.service.repository.MonedaCotizacionRepository;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
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

@Service
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
            LocalDate fecha = LocalDate.parse(resultado.fecha);
            cotizacionesPorFecha.put(fecha, detalle.tipoCotizacion);
        }

        List<MonedaCotizacion> cotizacionesNuevas = new ArrayList<>();
        LocalDate fechaDesdeLocal = fechaDesde.toLocalDate();
        LocalDate fechaHastaLocal = fechaHasta.toLocalDate();
        
        // Iterar por cada fecha en el rango solicitado
        LocalDate currentDate = fechaDesdeLocal;
        while (!currentDate.isAfter(fechaHastaLocal)) {
            OffsetDateTime fechaActual = currentDate.atStartOfDay().atOffset(ZoneOffset.UTC);
            
            // Solo procesar si la fecha no existe
            if (!repository.existsByMonedaIdAndFecha(monedaId, fechaActual)) {
                // Buscar la cotización para la fecha actual o la anterior más cercana
                Map.Entry<LocalDate, Double> cotizacionEntry = cotizacionesPorFecha.floorEntry(currentDate);
                
                if (cotizacionEntry != null) {
                    // Determinar si es una cotización copiada
                    byte esCopia = (byte) (cotizacionEntry.getKey().equals(currentDate) ? 0 : 1);
                    
                    MonedaCotizacion cotizacion = new MonedaCotizacion(
                        null,
                        monedaId,
                        fechaActual,
                        BigDecimal.valueOf(cotizacionEntry.getValue()),
                        esCopia
                    );
                    
                    cotizacionesNuevas.add(cotizacion);
                }
            }
            
            currentDate = currentDate.plusDays(1);
        }

        return repository.saveAll(cotizacionesNuevas);
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
                    BigDecimal.ONE,  // Cotización siempre 1
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
