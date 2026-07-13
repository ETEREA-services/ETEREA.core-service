package eterea.core.service.hexagonal.invoicedata.infrastructure.mapper;

import eterea.core.service.hexagonal.invoicedata.infrastructure.dto.ClienteInvoiceDataResponse;
import eterea.core.service.kotlin.model.Cliente;
import org.springframework.stereotype.Component;

@Component
public class ClienteMapper {

    public ClienteInvoiceDataResponse toResponse(Cliente cliente) {
        if (cliente == null) {
            return null;
        }
        return ClienteInvoiceDataResponse.builder()
                .razonSocial(cliente.getRazonSocial())
                .domicilio(cliente.getDomicilio())
                .cuit(cliente.getCuit())
                .posicionIva(cliente.getPosicionIva())
                .email(cliente.getEmail())
                .build();
    }

}
