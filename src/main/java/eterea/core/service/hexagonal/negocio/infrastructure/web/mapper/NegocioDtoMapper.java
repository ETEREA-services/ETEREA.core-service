package eterea.core.service.hexagonal.negocio.infrastructure.web.mapper;

import eterea.core.service.hexagonal.negocio.domain.model.Negocio;
import eterea.core.service.hexagonal.negocio.infrastructure.web.dto.NegocioResponse;
import org.springframework.stereotype.Component;

@Component
public class NegocioDtoMapper {

    public NegocioResponse toResponse(Negocio domain) {
        if (domain == null) {
            return null;
        }
        return NegocioResponse.builder()
                .negocioId(domain.getNegocioId())
                .nombre(domain.getNombre())
                .negocioIdReal(domain.getNegocioIdReal())
                .databaseIpVpn(domain.getDatabaseIpVpn())
                .databaseIpLan(domain.getDatabaseIpLan())
                .database(domain.getDatabase())
                .user(domain.getUser())
                .transferenciaStock(domain.getTransferenciaStock())
                .transferenciaValor(domain.getTransferenciaValor())
                .backendIpVpn(domain.getBackendIpVpn())
                .backendIpLan(domain.getBackendIpLan())
                .backendPort(domain.getBackendPort())
                .hasGateway(domain.getHasGateway())
                .copyArticulo(domain.getCopyArticulo())
                .ipAddress(domain.getIpAddress())
                .backendServer(domain.getBackendServer())
                .build();
    }

}
