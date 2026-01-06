package eterea.core.service.hexagonal.negocio.infrastructure.persistence.mapper;

import eterea.core.service.hexagonal.negocio.domain.model.Negocio;
import eterea.core.service.hexagonal.negocio.infrastructure.persistence.entity.NegocioEntity;
import org.springframework.stereotype.Component;

@Component
public class NegocioMapper {

    public Negocio toDomainModel(NegocioEntity entity) {
        if (entity == null) {
            return null;
        }
        return new Negocio(
                entity.getNegocioId(),
                entity.getNombre(),
                entity.getNegocioIdReal(),
                entity.getDatabaseIpVpn(),
                entity.getDatabaseIpLan(),
                entity.getDatabase(),
                entity.getUser(),
                entity.getTransferenciaStock(),
                entity.getTransferenciaValor(),
                entity.getBackendIpVpn(),
                entity.getBackendIpLan(),
                entity.getBackendPort(),
                entity.getFacturaServer(),
                entity.getFacturaPort(),
                entity.getHasGateway(),
                entity.getCopyArticulo(),
                null,
                null
        );
    }

}
