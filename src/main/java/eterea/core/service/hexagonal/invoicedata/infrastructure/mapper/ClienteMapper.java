package eterea.core.service.hexagonal.invoicedata.infrastructure.mapper;

import eterea.core.service.hexagonal.invoicedata.infrastructure.dto.ClienteResponse;
import eterea.core.service.kotlin.model.Cliente;
import org.springframework.stereotype.Component;

@Component
public class ClienteMapper {

    public ClienteResponse toResponse(Cliente cliente) {
        if (cliente == null) {
            return null;
        }
        return ClienteResponse.builder()
                .razonSocial(cliente.getRazonSocial())
                .domicilio(cliente.getDomicilio())
                .cuit(cliente.getCuit())
                .posicionIva(cliente.getPosicionIva())
                .email(cliente.getEmail())
                .build();
    }

}
