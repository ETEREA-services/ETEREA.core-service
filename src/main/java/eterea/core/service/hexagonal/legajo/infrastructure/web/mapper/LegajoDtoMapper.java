package eterea.core.service.hexagonal.legajo.infrastructure.web.mapper;

import eterea.core.service.hexagonal.legajo.domain.model.Legajo;
import eterea.core.service.hexagonal.legajo.infrastructure.web.dto.LegajoRequest;
import eterea.core.service.hexagonal.legajo.infrastructure.web.dto.LegajoResponse;
import org.springframework.stereotype.Component;

@Component
public class LegajoDtoMapper {

    public LegajoResponse toResponse(Legajo legajo) {
        if (legajo == null) {
            return null;
        }
        return LegajoResponse.builder()
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
                .build();
    }

    public Legajo toDomain(LegajoRequest legajoRequest) {
        if (legajoRequest == null) {
            return null;
        }
        return Legajo.builder()
                .legajoId(legajoRequest.getLegajoId())
                .apellido(legajoRequest.getApellido())
                .nombre(legajoRequest.getNombre())
                .documentoId(legajoRequest.getDocumentoId())
                .telefono(legajoRequest.getTelefono())
                .celular(legajoRequest.getCelular())
                .email(legajoRequest.getEmail())
                .fechaNacimiento(legajoRequest.getFechaNacimiento())
                .numeroDocumento(legajoRequest.getNumeroDocumento())
                .cuil(legajoRequest.getCuil())
                .calle(legajoRequest.getCalle())
                .numero(legajoRequest.getNumero())
                .piso(legajoRequest.getPiso())
                .departamento(legajoRequest.getDepartamento())
                .localidad(legajoRequest.getLocalidad())
                .provincia(legajoRequest.getProvincia())
                .fechaIngreso(legajoRequest.getFechaIngreso())
                .build();
    }
}
