package eterea.core.service.hexagonal.invoicedata.infrastructure.mapper;

import eterea.core.service.hexagonal.invoicedata.domain.model.InvoiceData;
import eterea.core.service.hexagonal.invoicedata.infrastructure.dto.InvoiceDataResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class InvoiceDataMapper {

    private final ClienteMovimientoMapper clienteMovimientoMapper;
    private final RegistroCaeMapper registroCaeMapper;

    public InvoiceDataResponse toResponse(InvoiceData invoiceData) {
        log.debug("\n\nProcessing InvoiceDataMapper.toResponse\n\n");
        if (invoiceData == null) {
            return null;
        }
        var clienteMovimiento = clienteMovimientoMapper.toResponse(invoiceData.getClienteMovimiento());
        log.debug("\n\nClienteMovimiento -> {}\n\n", clienteMovimiento.jsonify());
        var registroCae = registroCaeMapper.toResponse(invoiceData.getRegistroCae());
        log.debug("\n\nRegistroCae -> {}\n\n", registroCae.jsonify());
        var clienteMovimientoAsociado = clienteMovimientoMapper.toResponse(invoiceData.getClienteMovimientoAsociado());
        if (clienteMovimientoAsociado != null) {
            log.debug("\n\nClienteMovimientoAsociado -> {}\n\n", clienteMovimientoAsociado.jsonify());
        }
        var invoiceDataResponse = InvoiceDataResponse.builder()
                .clienteMovimiento(clienteMovimiento)
                .registroCae(registroCae)
                .clienteMovimientoAsociado(clienteMovimientoAsociado)
                .build();
        log.debug("\n\nInvoiceDataResponse -> {}\n\n", invoiceDataResponse.jsonify());
        return invoiceDataResponse;
    }

}
