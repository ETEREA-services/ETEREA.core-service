package eterea.core.service.hexagonal.invoicedata.application.usecases;

import eterea.core.service.hexagonal.invoicedata.domain.model.InvoiceData;
import eterea.core.service.hexagonal.invoicedata.domain.ports.in.GetInvoiceDataByClienteMovimientoId;
import eterea.core.service.model.ClienteMovimiento;
import eterea.core.service.service.ClienteMovimientoService;
import eterea.core.service.service.RegistroCaeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetInvoiceDataByClienteMovimientoIdImpl implements GetInvoiceDataByClienteMovimientoId {

    private final ClienteMovimientoService clienteMovimientoService;
    private final RegistroCaeService registroCaeService;

    @Override
    public InvoiceData getInvoiceDataByClienteMovimientoId(Long clienteMovimientoId) {
        var clienteMovimiento = clienteMovimientoService.findByClienteMovimientoId(clienteMovimientoId);
        var registroCae = registroCaeService.findByUnique(
                clienteMovimiento.getComprobanteId(),
                clienteMovimiento.getPuntoVenta(),
                clienteMovimiento.getNumeroComprobante());
        ClienteMovimiento clienteMovimientoAsociado = null;
        if (registroCae.getClienteMovimientoIdAsociado() != null) {
            clienteMovimientoAsociado = clienteMovimientoService.findByClienteMovimientoId(registroCae.getClienteMovimientoIdAsociado());
        }

        return InvoiceData.builder()
                .clienteMovimiento(clienteMovimiento)
                .registroCae(registroCae)
                .clienteMovimientoAsociado(clienteMovimientoAsociado)
                .build();
    }

}
