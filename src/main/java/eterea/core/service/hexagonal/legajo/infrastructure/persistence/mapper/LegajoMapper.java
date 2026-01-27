package eterea.core.service.hexagonal.legajo.infrastructure.persistence.mapper;

import eterea.core.service.hexagonal.legajo.domain.model.Legajo;
import eterea.core.service.hexagonal.legajo.infrastructure.persistence.entity.LegajoEntity;
import org.springframework.stereotype.Component;

@Component
public class LegajoMapper {

    public Legajo toDomainModel(LegajoEntity legajoEntity) {
        if (legajoEntity == null) {
            return null;
        }
        return Legajo.builder()
                .legajoId(legajoEntity.getLegajoId())
                .apellido(legajoEntity.getApellido())
                .nombre(legajoEntity.getNombre())
                .documentoId(legajoEntity.getDocumentoId())
                .telefono(legajoEntity.getTelefono())
                .celular(legajoEntity.getCelular())
                .email(legajoEntity.getEmail())
                .fechaNacimiento(legajoEntity.getFechaNacimiento())
                .numeroDocumento(legajoEntity.getNumeroDocumento())
                .cuil(legajoEntity.getCuil())
                .calle(legajoEntity.getCalle())
                .numero(legajoEntity.getNumero())
                .piso(legajoEntity.getPiso())
                .departamento(legajoEntity.getDepartamento())
                .localidad(legajoEntity.getLocalidad())
                .provincia(legajoEntity.getProvincia())
                .fechaIngreso(legajoEntity.getFechaIngreso())
                .negocioId(legajoEntity.getNegocioId())
                .build();
    }

    public LegajoEntity toLegajoEntity(Legajo legajo) {
        if (legajo == null) {
            return null;
        }
        return LegajoEntity.builder()
                .legajoId(legajo.getLegajoId())
                .apellido(legajo.getApellido())
                .nombre(legajo.getNombre())
                .documentoId(legajo.getDocumentoId())
                .telefono(legajo.getTelefono())
                .celular(legajo.getCelular())
                .email(legajo.getEmail())
                .fechaNacimiento(legajo.getFechaNacimiento())
                .numeroDocumento(legajo.getNumeroDocumento())
                .cuil(legajo.getCuil())
                .calle(legajo.getCalle())
                .numero(legajo.getNumero())
                .piso(legajo.getPiso())
                .departamento(legajo.getDepartamento())
                .localidad(legajo.getLocalidad())
                .provincia(legajo.getProvincia())
                .fechaIngreso(legajo.getFechaIngreso())
                .negocioId(legajo.getNegocioId())
                .build();
    }

}
