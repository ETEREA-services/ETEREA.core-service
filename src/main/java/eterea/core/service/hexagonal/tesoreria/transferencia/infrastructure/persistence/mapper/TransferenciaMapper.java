package eterea.core.service.hexagonal.tesoreria.transferencia.infrastructure.persistence.mapper;

import eterea.core.service.hexagonal.tesoreria.transferencia.domain.model.Transferencia;
import eterea.core.service.hexagonal.tesoreria.transferencia.infrastructure.persistence.entity.TransferenciaEntity;
import org.springframework.stereotype.Component;

@Component
public class TransferenciaMapper {

    public Transferencia toDomain(TransferenciaEntity entity) {
        if (entity == null) {
            return null;
        }
        return Transferencia.builder()
                .transferenciaId(entity.getTransferenciaId())
                .negocioIdDesde(entity.getNegocioIdDesde())
                .negocioIdHasta(entity.getNegocioIdHasta())
                .numeroTransferencia(entity.getNumeroTransferencia())
                .fecha(entity.getFecha())
                .total(entity.getTotal())
                .ordenContable(entity.getOrdenContable())
                .transferido(entity.getTransferido())
                .comprobanteId(entity.getComprobanteId())
                .fechaOtro(entity.getFechaOtro())
                .build();
    }

    public TransferenciaEntity toEntity(Transferencia domain) {
        if (domain == null) {
            return null;
        }
        TransferenciaEntity entity = new TransferenciaEntity();
        entity.setTransferenciaId(domain.getTransferenciaId());
        entity.setNegocioIdDesde(domain.getNegocioIdDesde());
        entity.setNegocioIdHasta(domain.getNegocioIdHasta());
        entity.setNumeroTransferencia(domain.getNumeroTransferencia());
        entity.setFecha(domain.getFecha());
        entity.setTotal(domain.getTotal());
        entity.setOrdenContable(domain.getOrdenContable());
        entity.setTransferido(domain.getTransferido());
        entity.setComprobanteId(domain.getComprobanteId());
        entity.setFechaOtro(domain.getFechaOtro());
        return entity;
    }
}
