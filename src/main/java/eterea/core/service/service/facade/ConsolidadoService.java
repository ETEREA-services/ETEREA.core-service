package eterea.core.service.service.facade;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import eterea.core.service.kotlin.model.ClienteMovimiento;
import eterea.core.service.kotlin.model.ComprobanteFaltante;
import eterea.core.service.service.ClienteMovimientoService;
import eterea.core.service.service.ComprobanteFaltanteService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Slf4j
public class ConsolidadoService {

    private final ComprobanteFaltanteService comprobanteFaltanteService;
    private final ClienteMovimientoService clienteMovimientoService;

    public ConsolidadoService(ComprobanteFaltanteService comprobanteFaltanteService,
                              ClienteMovimientoService clienteMovimientoService) {
        this.comprobanteFaltanteService = comprobanteFaltanteService;
        this.clienteMovimientoService = clienteMovimientoService;
    }

    @Transactional
    public String fillFaltantesFecha(OffsetDateTime fecha) {
        log.debug("Processing ConsolidadoService.fillFaltantesFecha");
        log.debug("deleting all faltantes by fecha");
        comprobanteFaltanteService.deleteAllByFecha(fecha);
        log.debug("deleting all 0 by fecha");
        clienteMovimientoService.deleteAll0ByFecha(fecha);

        // Carga todos los comprobantes facturados por fecha
        var comprobantesFacturadosByFecha = clienteMovimientoService.findAllFacturadosByFecha(fecha);
        logFacturados(comprobantesFacturadosByFecha);

        // Tomar de la lista comprobantesFacturadosByFecha por grupos de letraComprobantes y puntoVenta y buscar los números faltantes entre el mínimo y el máximo encontrado
        var groupedByLetraAndPuntoVenta = comprobantesFacturadosByFecha.stream()
                .collect(Collectors.groupingBy(cm -> 
                    cm.getLetraComprobante() + "." + 
                    cm.getPuntoVenta() + "." + 
                    (cm.getComprobante() != null ? cm.getComprobante().getDebita() : "0") + "." +
                    (cm.getComprobante() != null ? cm.getComprobante().getComprobanteAfipId() : "0")
                ));
        logGrupos(groupedByLetraAndPuntoVenta);

        List<ComprobanteFaltante> faltantes = groupedByLetraAndPuntoVenta.entrySet().stream()
            .flatMap(entry -> {
                String key = entry.getKey();
                List<ClienteMovimiento> list = entry.getValue();
                log.debug("Processing key {}", key);
                long minNumero = list.stream().mapToLong(ClienteMovimiento::getNumeroComprobante).min().orElse(0L);
                var minComprobante = list.stream()
                    .filter(cm -> cm.getNumeroComprobante() == minNumero)
                    .findFirst()
                    .orElse(null);
                logClienteMovimiento(minComprobante);
                if (minComprobante == null) {
                    log.error("No se encontró el comprobante con número mínimo {} para la key {}", minNumero, key);
                    return Stream.empty();
                }
                var negocioId = minComprobante.getNegocioId();
                var comprobanteId = minComprobante.getComprobanteId();
                var puntoVenta = minComprobante.getPuntoVenta();
                long maxNumero = list.stream().mapToLong(ClienteMovimiento::getNumeroComprobante).max().orElse(0L);
                log.debug("maxNumero {}", maxNumero);

                List<ComprobanteFaltante> faltantesGrupo = new ArrayList<>();
                for (long numeroComprobante = minNumero; numeroComprobante <= maxNumero; numeroComprobante++) {
                    long finalNumeroComprobante = numeroComprobante;
                    boolean exists = list.stream().anyMatch(cm -> cm.getNumeroComprobante() == finalNumeroComprobante);
                    if (!exists) {
                        log.debug("Adding missing comprobante to faltantes -> {}", finalNumeroComprobante);
                        faltantesGrupo.add(comprobanteFaltanteService.createOne(negocioId, comprobanteId, fecha, puntoVenta, numeroComprobante));
                    }
                }
                return faltantesGrupo.stream();
            })
            .collect(Collectors.toList());

        faltantes = comprobanteFaltanteService.saveAll(faltantes);
        logFaltantes(faltantes);

        return "OK";
    }

    private void logFaltantes(List<ComprobanteFaltante> faltantes) {
        log.debug("Processing ConsolidadoService.logFaltantes");
        try {
            log.debug("Faltantes -> {}", JsonMapper
                    .builder()
                    .findAndAddModules()
                    .build()
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(faltantes));
        } catch (JsonProcessingException e) {
            log.debug("Faltantes jsonify error -> {}", e.getMessage());
        }
    }

    private void logClienteMovimiento(ClienteMovimiento minComprobante) {
        log.debug("Processing ConsolidadoService.logClienteMovimiento");
        try {
            log.debug("ClienteMovimiento -> {}", JsonMapper
                    .builder()
                    .findAndAddModules()
                    .build()
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(minComprobante));
        } catch (JsonProcessingException e) {
            log.debug("ClienteMovimiento jsonify error -> {}", e.getMessage());
        }
    }

    private void logGrupos(Map<String, List<ClienteMovimiento>> groupedByLetraAndPrefijo) {
        log.debug("Processing ConsolidadoService.logGrupos");
        try {
            Map<String, Map<String, Long>> gruposResumidos = groupedByLetraAndPrefijo.entrySet().stream()
                .collect(Collectors.toMap(
                    Map.Entry::getKey,
                    entry -> {
                        List<ClienteMovimiento> movimientos = entry.getValue();
                        Long min = movimientos.stream()
                            .mapToLong(ClienteMovimiento::getNumeroComprobante)
                            .min()
                            .orElse(0L);
                        Long max = movimientos.stream()
                            .mapToLong(ClienteMovimiento::getNumeroComprobante)
                            .max()
                            .orElse(0L);
                        return Map.of("min", min, "max", max);
                    }
                ));

            log.debug("Grupos -> {}", JsonMapper
                    .builder()
                    .findAndAddModules()
                    .build()
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(gruposResumidos));
        } catch (JsonProcessingException e) {
            log.debug("Grupos jsonify error -> {}", e.getMessage());
        }
    }

    private void logFacturados(List<ClienteMovimiento> facturados) {
        log.debug("Processing ConsolidadoService.logFacturados");
        try {
            log.debug("Facturados -> {}", JsonMapper
                    .builder()
                    .findAndAddModules()
                    .build()
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(facturados));
        } catch (JsonProcessingException e) {
            log.debug("Facturados jsonify error -> {}", e.getMessage());
        }
    }

}
