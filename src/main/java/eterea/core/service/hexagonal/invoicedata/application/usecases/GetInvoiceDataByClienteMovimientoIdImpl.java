package eterea.core.service.hexagonal.invoicedata.application.usecases;

import eterea.core.service.hexagonal.ventas.clientemovimiento.domain.model.ClienteMovimiento;
import eterea.core.service.hexagonal.invoicedata.domain.model.InvoiceData;
import eterea.core.service.hexagonal.invoicedata.domain.ports.in.GetInvoiceDataByClienteMovimientoId;
import eterea.core.service.hexagonal.ventas.clientemovimiento.application.service.ClienteMovimientoService;
import eterea.core.service.service.RegistroCaeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class GetInvoiceDataByClienteMovimientoIdImpl implements GetInvoiceDataByClienteMovimientoId {

    private final ClienteMovimientoService clienteMovimientoService;
    private final RegistroCaeService registroCaeService;

    @Override
    public InvoiceData getInvoiceDataByClienteMovimientoId(Long clienteMovimientoId) {
        log.debug("\n\nProcessing GetInvoiceDataByClienteMovimientoIdImpl\n\n");
        var clienteMovimiento = clienteMovimientoService.findByClienteMovimientoId(clienteMovimientoId);
        log.debug("\n\nClienteMovimiento -> {}\n\n", clienteMovimiento.jsonify());
        var registroCae = registroCaeService.findByUnique(
                clienteMovimiento.getComprobanteId(),
                clienteMovimiento.getPuntoVenta(),
                clienteMovimiento.getNumeroComprobante());
        log.debug("\n\nRegistroCae -> {}\n\n", registroCae.jsonify());
        ClienteMovimiento clienteMovimientoAsociado = null;
        if (registroCae.getClienteMovimientoIdAsociado() != null) {
            clienteMovimientoAsociado = clienteMovimientoService.findByClienteMovimientoId(registroCae.getClienteMovimientoIdAsociado());
            log.debug("\n\nClienteMovimientoAsociado -> {}\n\n", clienteMovimientoAsociado.jsonify());
        }

        var invoiceData = InvoiceData.builder()
                .clienteMovimiento(clienteMovimiento)
                .registroCae(registroCae)
                .clienteMovimientoAsociado(clienteMovimientoAsociado)
                .build();
        log.debug("\n\nInvoiceData -> {}\n\n", invoiceData.jsonify());
        return invoiceData;
    }

}
