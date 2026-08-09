package eterea.core.service.hexagonal.compras.proveedor.infrastructure.persistence.mapper;

import eterea.core.service.hexagonal.compras.proveedor.domain.model.Proveedor;
import eterea.core.service.hexagonal.compras.proveedor.infrastructure.persistence.entity.ProveedorEntity;
import org.springframework.stereotype.Component;

@Component
public class ProveedorMapper {

    public ProveedorEntity toEntity(Proveedor domain) {
        if (domain == null) {
            return null;
        }
        ProveedorEntity.ProveedorEntityBuilder builder = ProveedorEntity.builder()
                .proveedorId(domain.getProveedorId())
                .negocioId(domain.getNegocioId())
                .ingresosBrutosContribuyenteId(domain.getIngresosBrutosContribuyenteId())
                .proveedorCategoriaId(domain.getProveedorCategoriaId())
                .ingresosBrutosCategoriaId(domain.getIngresosBrutosCategoriaId())
                .reparticionId(domain.getReparticionId());
        if (domain.getRazonSocial() != null) {
            builder.razonSocial(domain.getRazonSocial());
        }
        if (domain.getCuit() != null) {
            builder.cuit(domain.getCuit());
        }
        if (domain.getDomicilio() != null) {
            builder.domicilio(domain.getDomicilio());
        }
        if (domain.getTelefono() != null) {
            builder.telefono(domain.getTelefono());
        }
        if (domain.getFax() != null) {
            builder.fax(domain.getFax());
        }
        if (domain.getEmail() != null) {
            builder.email(domain.getEmail());
        }
        if (domain.getPosicion() != null) {
            builder.posicion(domain.getPosicion());
        }
        if (domain.getCelular() != null) {
            builder.celular(domain.getCelular());
        }
        if (domain.getNumeroInscripcionIngresosBrutos() != null) {
            builder.numeroInscripcionIngresosBrutos(domain.getNumeroInscripcionIngresosBrutos());
        }
        if (domain.getTransporte() != null) {
            builder.transporte(domain.getTransporte());
        }
        return builder.build();
    }

    public Proveedor toDomain(ProveedorEntity entity) {
        if (entity == null) {
            return null;
        }
        Proveedor.ProveedorBuilder builder = Proveedor.builder()
                .proveedorId(entity.getProveedorId())
                .negocioId(entity.getNegocioId())
                .ingresosBrutosContribuyenteId(entity.getIngresosBrutosContribuyenteId())
                .proveedorCategoriaId(entity.getProveedorCategoriaId())
                .ingresosBrutosCategoriaId(entity.getIngresosBrutosCategoriaId())
                .reparticionId(entity.getReparticionId());
        if (entity.getRazonSocial() != null) {
            builder.razonSocial(entity.getRazonSocial());
        }
        if (entity.getCuit() != null) {
            builder.cuit(entity.getCuit());
        }
        if (entity.getDomicilio() != null) {
            builder.domicilio(entity.getDomicilio());
        }
        if (entity.getTelefono() != null) {
            builder.telefono(entity.getTelefono());
        }
        if (entity.getFax() != null) {
            builder.fax(entity.getFax());
        }
        if (entity.getEmail() != null) {
            builder.email(entity.getEmail());
        }
        if (entity.getPosicion() != null) {
            builder.posicion(entity.getPosicion());
        }
        if (entity.getCelular() != null) {
            builder.celular(entity.getCelular());
        }
        if (entity.getNumeroInscripcionIngresosBrutos() != null) {
            builder.numeroInscripcionIngresosBrutos(entity.getNumeroInscripcionIngresosBrutos());
        }
        if (entity.getTransporte() != null) {
            builder.transporte(entity.getTransporte());
        }
        return builder.build();
    }

}
