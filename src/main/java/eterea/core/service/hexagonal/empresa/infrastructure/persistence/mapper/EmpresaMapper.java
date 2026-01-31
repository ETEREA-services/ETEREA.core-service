package eterea.core.service.hexagonal.empresa.infrastructure.persistence.mapper;

import eterea.core.service.hexagonal.empresa.domain.model.Empresa;
import eterea.core.service.hexagonal.empresa.infrastructure.persistence.entity.EmpresaEntity;
import eterea.core.service.hexagonal.invoicedata.infrastructure.dto.EmpresaResponse;
import org.springframework.stereotype.Component;

@Component
public class EmpresaMapper {

    public Empresa toDomainModel(EmpresaEntity empresaEntity) {
        if (empresaEntity == null) {
            return null;
        }
        return Empresa.builder()
                .empresaId(empresaEntity.getEmpresaId())
                .negocioId(empresaEntity.getNegocioId())
                .razonSocial(empresaEntity.getRazonSocial())
                .nombreFantasia(empresaEntity.getNombreFantasia())
                .domicilio(empresaEntity.getDomicilio())
                .telefono(empresaEntity.getTelefono())
                .cuit(empresaEntity.getCuit())
                .ingresosBrutos(empresaEntity.getIngresosBrutos())
                .numeroEstablecimiento(empresaEntity.getNumeroEstablecimiento())
                .sedeTimbrado(empresaEntity.getSedeTimbrado())
                .inicioActividades(empresaEntity.getInicioActividades())
                .condicionIva(empresaEntity.getCondicionIva())
                .unidadNegocio(empresaEntity.getUnidadNegocio())
                .diaInicial(empresaEntity.getDiaInicial())
                .negocioIdComision(empresaEntity.getNegocioIdComision())
                .conectaUnificado(empresaEntity.getConectaUnificado())
                .certificado(empresaEntity.getCertificado())
                .businessId(empresaEntity.getBusinessId())
                .build();
    }

    public EmpresaEntity toEntity(Empresa empresa) {
        if (empresa == null) {
            return null;
        }
        return EmpresaEntity.builder()
                .empresaId(empresa.getEmpresaId())
                .negocioId(empresa.getNegocioId())
                .razonSocial(empresa.getRazonSocial())
                .nombreFantasia(empresa.getNombreFantasia())
                .domicilio(empresa.getDomicilio())
                .telefono(empresa.getTelefono())
                .cuit(empresa.getCuit())
                .ingresosBrutos(empresa.getIngresosBrutos())
                .numeroEstablecimiento(empresa.getNumeroEstablecimiento())
                .sedeTimbrado(empresa.getSedeTimbrado())
                .inicioActividades(empresa.getInicioActividades())
                .condicionIva(empresa.getCondicionIva())
                .unidadNegocio(empresa.getUnidadNegocio())
                .diaInicial(empresa.getDiaInicial())
                .negocioIdComision(empresa.getNegocioIdComision())
                .conectaUnificado(empresa.getConectaUnificado())
                .certificado(empresa.getCertificado())
                .businessId(empresa.getBusinessId())
                .build();
    }

    public EmpresaResponse toResponse(EmpresaEntity empresaEntity) {
        if (empresaEntity == null) {
            return null;
        }
        return EmpresaResponse.builder()
                .nombreFantasia(empresaEntity.getNombreFantasia())
                .razonSocial(empresaEntity.getRazonSocial())
                .domicilio(empresaEntity.getDomicilio())
                .telefono(empresaEntity.getTelefono())
                .condicionIva(empresaEntity.getCondicionIva())
                .cuit(empresaEntity.getCuit())
                .ingresosBrutos(empresaEntity.getIngresosBrutos())
                .inicioActividades(empresaEntity.getInicioActividades())
                .build();
    }

}
