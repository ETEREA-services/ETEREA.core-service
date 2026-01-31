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
        if (invoiceData == null) {
            return null;
        }
        var clienteMovimiento = clienteMovimientoMapper.toResponse(invoiceData.getClienteMovimiento());
        var registroCae = registroCaeMapper.toResponse(invoiceData.getRegistroCae());
        var clienteMovimientoAsociado = clienteMovimientoMapper.toResponse(invoiceData.getClienteMovimientoAsociado());
        return InvoiceDataResponse.builder()
                .clienteMovimiento(clienteMovimiento)
                .registroCae(registroCae)
                .clienteMovimientoAsociado(clienteMovimientoAsociado)
                .build();
    }

}
