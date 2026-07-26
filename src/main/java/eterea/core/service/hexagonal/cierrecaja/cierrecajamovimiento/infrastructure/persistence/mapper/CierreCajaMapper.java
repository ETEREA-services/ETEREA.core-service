package eterea.core.service.hexagonal.cierrecaja.cierrecajamovimiento.infrastructure.persistence.mapper;

import eterea.core.service.hexagonal.cierrecaja.cierrecajamovimiento.domain.model.CierreCaja;
import eterea.core.service.hexagonal.cierrecaja.cierrecajamovimiento.infrastructure.persistence.entity.CierreCajaEntity;
import eterea.core.service.hexagonal.negocio.infrastructure.persistence.mapper.NegocioMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CierreCajaMapper {

    private final NegocioMapper negocioMapper;

    public CierreCaja toDomain(CierreCajaEntity entity) {
        if (entity == null) {
            return null;
        }
        return CierreCaja.builder()
                .cierreCajaId(entity.getCierreCajaId())
                .puntoVenta(entity.getPuntoVenta())
                .negocioId(entity.getNegocioId())
                .fechaTope(entity.getFechaTope())
                .fechaCierre(entity.getFechaCierre())
                .usuario(entity.getUsuario())
                .nivel(entity.getNivel())
                .negocioIdDesde(entity.getNegocioIdDesde())
                .negocioIdHasta(entity.getNegocioIdHasta())
                .transferenciaId(entity.getTransferenciaId())
                .enviado(entity.getEnviado())
                .id(entity.getId())
                .negocio(negocioMapper.toDomainModel(entity.getNegocio()))
                .build();
    }

    public CierreCajaEntity toEntity(CierreCaja domain) {
        if (domain == null) {
            return null;
        }
        return CierreCajaEntity.builder()
                .cierreCajaId(domain.getCierreCajaId())
                .puntoVenta(domain.getPuntoVenta())
                .negocioId(domain.getNegocioId())
                .fechaTope(domain.getFechaTope())
                .fechaCierre(domain.getFechaCierre())
                .usuario(domain.getUsuario())
                .nivel(domain.getNivel())
                .negocioIdDesde(domain.getNegocioIdDesde())
                .negocioIdHasta(domain.getNegocioIdHasta())
                .transferenciaId(domain.getTransferenciaId())
                .enviado(domain.getEnviado())
                .id(domain.getId())
                .build();
    }

}
