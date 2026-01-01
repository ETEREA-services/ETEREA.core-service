package eterea.core.service.hexagonal.facturacion.arca.exportacion.application.service;

import eterea.core.service.client.arca.FacturacionAfipClient;
import eterea.core.service.hexagonal.facturacion.arca.exportacion.application.ports.out.FacturaExportacionFacturadorItem;
import eterea.core.service.hexagonal.facturacion.arca.exportacion.application.ports.out.FacturaExportacionFacturadorPayloadRequest;
import eterea.core.service.hexagonal.facturacion.arca.exportacion.infrastructure.web.dto.FacturaExportacionContextRequest;
import eterea.core.service.hexagonal.facturacion.arca.exportacion.infrastructure.web.dto.FacturaExportacionResponse;
import eterea.core.service.model.RegistroCae;
import eterea.core.service.service.*;
import eterea.core.service.tool.Jsonifier;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class FacturaElectronicaExportacionService {

    private final ComprobanteService comprobanteService;
    private final MonedaService monedaService;
    private final ClienteService clienteService;
    private final ArticuloMovimientoTemporalService articuloMovimientoTemporalService;
    private final FacturacionAfipClient facturacionAfipClient;
    private final RegistroCaeService registroCaeService;

    public FacturaExportacionResponse makeFactura(FacturaExportacionContextRequest facturaExportacionContextRequest) {
        var comprobante = comprobanteService.findByComprobanteId(facturaExportacionContextRequest.getComprobanteId());
        if (comprobante.getFacturaElectronica() == 0) {
            return null;
        }
        var moneda = monedaService.findByMonedaId(facturaExportacionContextRequest.getMonedaId());
        var monedaDefinition = "PES";
        if (Objects.equals(moneda.getSimbolo(), "USD")) {
            monedaDefinition = "DOL";
        }
        var cliente = clienteService.findByClienteId(facturaExportacionContextRequest.getClienteId());

        var items = articuloMovimientoTemporalService.findAllByHWnd(facturaExportacionContextRequest.getIpAddress(), facturaExportacionContextRequest.getHandler(), null).stream()
                .map(movimiento -> new FacturaExportacionFacturadorItem(
                        movimiento.getArticuloId(),
                        movimiento.getDescripcion(),
                        movimiento.getCantidad().abs().intValue(),
                        7,
                        movimiento.getPrecioUnitario(),
                        movimiento.getTotal()))
                .toList();
        log.debug("Items -> {}", Jsonifier.builder(items).build());

        log.debug("Context -> {}", Jsonifier.builder(facturaExportacionContextRequest).build());

        var payload = FacturaExportacionFacturadorPayloadRequest.builder()
                .tipoAfip(comprobante.getComprobanteAfipId())
                .puntoVenta(comprobante.getPuntoVenta())
                .cliente(cliente.getRazonSocial())
                .domicilioCliente(cliente.getDomicilio())
                .codigoPais(facturaExportacionContextRequest.getCodigoPais())
                .idImpositivo(cliente.getIdImpositivo())
                .total(facturaExportacionContextRequest.getTotal())
                .monedaId(monedaDefinition)
                .cotizacion(facturaExportacionContextRequest.getCotizacion())
                .incoTerms(facturaExportacionContextRequest.getIncoTerms())
                .tipoExportacion(facturaExportacionContextRequest.getTipoExportacion())
                .permisoExistente(facturaExportacionContextRequest.getPermisoExistente())
                .items(items)
                .build();
        var response = facturacionAfipClient.facturadorExportacion(payload);
        log.debug("Response -> {}", Jsonifier.builder(response).build());

        if (response.getResultado().equals("A")) {
            var registroCae = RegistroCae.builder()
                    .comprobanteId(facturaExportacionContextRequest.getComprobanteId())
                    .puntoVenta(response.getPuntoVenta())
                    .numeroComprobante(response.getNumeroComprobante())
                    .clienteId(facturaExportacionContextRequest.getClienteId())
                    .total(facturaExportacionContextRequest.getTotal())
                    .cae(response.getCae())
                    .caeVencimiento(response.getVencimientoCae())
                    .fecha(response.getFechaComprobante())
                    .build();

            registroCae = registroCaeService.add(registroCae);
        }

        return response;
    }

}
