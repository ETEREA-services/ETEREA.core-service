package eterea.core.service.service.facade;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import eterea.core.service.kotlin.model.ClienteMovimiento;
import eterea.core.service.kotlin.model.Comprobante;
import eterea.core.service.kotlin.model.ComprobanteFaltante;
import eterea.core.service.service.ClienteMovimientoService;
import eterea.core.service.service.ComprobanteFaltanteService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ConsolidadoService {

    private final ComprobanteFaltanteService comprobanteFaltanteService;
    private final ClienteMovimientoService clienteMovimientoService;

    public record ComprobanteRango(
            Integer afipComprobanteId,
            String letraComprobante,
            Integer puntoVenta,
            Byte debita,
            Long minimoNumeroComprobante,
            Long maximoNumeroComprobante
    ) {
    }

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

        // Obtiene lista única de Comprobantes
        List<Comprobante> comprobantesUnicos = comprobantesFacturadosByFecha.stream()
                .map(ClienteMovimiento::getComprobante)
                .filter(Objects::nonNull)
                .distinct()
                .toList();
        logUnicos(comprobantesUnicos);

        // Procesa los comprobantes para obtener los rangos
        List<ComprobanteRango> rangosComprobantes = comprobantesFacturadosByFecha.stream()
                .filter(cm -> cm.getComprobante() != null && Objects.requireNonNull(cm.getComprobante()).getComprobanteAfipId() != null)
                .collect(Collectors.groupingBy(
                        cm -> new ComprobanteRango(
                                cm.getComprobante().getComprobanteAfipId(),
                                cm.getLetraComprobante(),
                                cm.getPuntoVenta(),
                                cm.getComprobante().getDebita(),
                                null,
                                null
                        ),
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                list -> new ComprobanteRango(
                                        Objects.requireNonNull(list.getFirst().getComprobante()).getComprobanteAfipId(),
                                        list.getFirst().getLetraComprobante(),
                                        list.getFirst().getPuntoVenta(),
                                        list.getFirst().getComprobante().getDebita(),
                                        list.stream().mapToLong(ClienteMovimiento::getNumeroComprobante).min().orElse(0L),
                                        list.stream().mapToLong(ClienteMovimiento::getNumeroComprobante).max().orElse(0L)
                                )
                        )
                ))
                .values()
                .stream()
                .toList();
        logRangos(rangosComprobantes);

        List<ComprobanteFaltante> faltantes = new ArrayList<>();
        for (ComprobanteRango rango : rangosComprobantes) {
            List<ClienteMovimiento> comprobantes = clienteMovimientoService.findAllFacturasByRango(rango.letraComprobante, rango.debita, rango.puntoVenta, rango.minimoNumeroComprobante, rango.maximoNumeroComprobante);
            var firstComprobante = comprobantes.getFirst();

            // Convertimos la lista de números de comprobante a un Set para búsquedas O(1)
            Set<Long> numerosComprobantes = comprobantes.stream()
                    .map(ClienteMovimiento::getNumeroComprobante)
                    .collect(Collectors.toSet());

            for (Long numeroComprobanteSearched = rango.minimoNumeroComprobante; numeroComprobanteSearched <= rango.maximoNumeroComprobante; numeroComprobanteSearched++) {
                if (!numerosComprobantes.contains(numeroComprobanteSearched)) {
                    log.debug("Comprobante faltante - Tipo: {}, Letra: {}, Punto Venta: {}, Número: {}",
                            rango.afipComprobanteId,
                            rango.letraComprobante,
                            rango.puntoVenta,
                            numeroComprobanteSearched);
                    faltantes.add(new ComprobanteFaltante.Builder()
                            .negocioId(firstComprobante.getNegocioId())
                            .comprobanteId(firstComprobante.getComprobanteId())
                            .fecha(firstComprobante.getFechaComprobante())
                            .prefijo(rango.puntoVenta)
                            .numero(numeroComprobanteSearched)
                            .build());
                }
            }
        }
        if (!faltantes.isEmpty()) {
            log.debug("Saving {} ComprobanteFaltante", faltantes.size());
            comprobanteFaltanteService.saveAll(faltantes);
        }

        return "OK";
    }

    private void logRangos(List<ComprobanteRango> rangosComprobantes) {
        try {
            log.debug("Rangos -> {}", JsonMapper
                    .builder()
                    .findAndAddModules()
                    .build()
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(rangosComprobantes));
        } catch (JsonProcessingException e) {
            log.debug("Rangos jsonify error -> {}", e.getMessage());
        }
    }

    private void logUnicos(List<Comprobante> comprobantesUnicos) {
        try {
            log.debug("Comprobantes unicos -> {}", JsonMapper
                    .builder()
                    .findAndAddModules()
                    .build()
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(comprobantesUnicos));
        } catch (JsonProcessingException e) {
            log.debug("Comprobantes unicos jsonify error -> {}", e.getMessage());
        }
    }
}
