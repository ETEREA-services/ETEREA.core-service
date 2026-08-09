package eterea.core.service.hexagonal.compras.proveedor.infrastructure.web.mapper;

import eterea.core.service.hexagonal.compras.proveedor.domain.model.Proveedor;
import eterea.core.service.hexagonal.compras.proveedor.infrastructure.web.dto.ProveedorResponse;
import org.springframework.stereotype.Component;

@Component
public class ProveedorDtoMapper {

    public ProveedorResponse toResponse(Proveedor domain) {
        if (domain == null) {
            return null;
        }
        return ProveedorResponse.builder()
                .proveedorId(domain.getProveedorId())
                .razonSocial(domain.getRazonSocial())
                .negocioId(domain.getNegocioId())
                .cuit(domain.getCuit())
                .domicilio(domain.getDomicilio())
                .telefono(domain.getTelefono())
                .fax(domain.getFax())
                .email(domain.getEmail())
                .posicion(domain.getPosicion())
                .celular(domain.getCelular())
                .numeroInscripcionIngresosBrutos(domain.getNumeroInscripcionIngresosBrutos())
                .ingresosBrutosContribuyenteId(domain.getIngresosBrutosContribuyenteId())
                .proveedorCategoriaId(domain.getProveedorCategoriaId())
                .ingresosBrutosCategoriaId(domain.getIngresosBrutosCategoriaId())
                .reparticionId(domain.getReparticionId())
                .transporte(domain.getTransporte())
                .build();
    }

}
