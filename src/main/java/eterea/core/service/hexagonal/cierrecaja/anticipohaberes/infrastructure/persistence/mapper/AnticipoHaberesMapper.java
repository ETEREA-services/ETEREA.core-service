package eterea.core.service.hexagonal.cierrecaja.anticipohaberes.infrastructure.persistence.mapper;

import eterea.core.service.hexagonal.cierrecaja.anticipohaberes.domain.model.CierreCajaAnticipoHaberes;
import eterea.core.service.hexagonal.cierrecaja.anticipohaberes.infrastructure.persistence.entity.CierreCajaAnticipoHaberesEntity;
import org.springframework.stereotype.Service;

@Service
public class AnticipoHaberesMapper {

    public CierreCajaAnticipoHaberesEntity toEntity(CierreCajaAnticipoHaberes cierreCajaAnticipoHaberes) {
        if (cierreCajaAnticipoHaberes == null) {
            return null;
        }
        return CierreCajaAnticipoHaberesEntity.builder()
                .cierreCajaId(cierreCajaAnticipoHaberes.getCierreCajaId())
                .legajoId(cierreCajaAnticipoHaberes.getLegajoId())
                .fecha(cierreCajaAnticipoHaberes.getFecha())
                .importe(cierreCajaAnticipoHaberes.getImporte())
                .userId(cierreCajaAnticipoHaberes.getUserId())
                .build();
    }

    public CierreCajaAnticipoHaberes toDomainModel(CierreCajaAnticipoHaberesEntity cierreCajaAnticipoHaberesEntity) {
        if (cierreCajaAnticipoHaberesEntity == null) {
            return null;
        }
        return CierreCajaAnticipoHaberes.builder()
                .cierreCajaId(cierreCajaAnticipoHaberesEntity.getCierreCajaId())
                .legajoId(cierreCajaAnticipoHaberesEntity.getLegajoId())
                .fecha(cierreCajaAnticipoHaberesEntity.getFecha())
                .importe(cierreCajaAnticipoHaberesEntity.getImporte())
                .userId(cierreCajaAnticipoHaberesEntity.getUserId())
                .build();
    }

}
