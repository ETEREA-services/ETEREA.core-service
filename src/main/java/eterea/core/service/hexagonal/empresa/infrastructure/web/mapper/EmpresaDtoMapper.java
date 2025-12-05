package eterea.core.service.hexagonal.empresa.infrastructure.web.mapper;

import eterea.core.service.hexagonal.empresa.domain.model.Empresa;
import eterea.core.service.hexagonal.empresa.infrastructure.web.dto.EmpresaResponse;
import org.springframework.stereotype.Component;

@Component
public class EmpresaDtoMapper {

    public EmpresaResponse toResponse(Empresa empresa) {
        if (empresa == null) {
            return null;
        }
        return EmpresaResponse.builder()
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

}
