package eterea.core.service.hexagonal.ventas.clientemovimiento.infrastructure.web.mapper;

import eterea.core.service.kotlin.model.Cliente;
import eterea.core.service.hexagonal.ventas.clientemovimiento.infrastructure.web.dto.ClienteResponse;
import org.springframework.stereotype.Component;

@Component
public class ClienteDtoMapper {

    public ClienteResponse toResponse(Cliente domain) {
        if (domain == null) {
            return null;
        }
        return ClienteResponse.builder()
                .clienteId(domain.getClienteId())
                .nombre(domain.getNombre())
                .negocioId(domain.getNegocioId())
                .cuit(domain.getCuit())
                .razonSocial(domain.getRazonSocial())
                .nombreFantasia(domain.getNombreFantasia())
                .fechaRestaurant(domain.getFechaRestaurant())
                .cantidadPaxs(domain.getCantidadPaxs())
                .tipoCliente(domain.getTipoCliente())
                .domicilio(domain.getDomicilio())
                .telefono(domain.getTelefono())
                .fax(domain.getFax())
                .email(domain.getEmail())
                .numeroMovil(domain.getNumeroMovil())
                .posicionIva(domain.getPosicionIva())
                .constante(domain.getConstante())
                .documentoId(domain.getDocumentoId())
                .tipoDocumento(domain.getTipoDocumento())
                .numeroDocumento(domain.getNumeroDocumento())
                .limiteCredito(domain.getLimiteCredito())
                .nacionalidad(domain.getNacionalidad())
                .clienteCategoriaId(domain.getClienteCategoriaId())
                .idImpositivo(domain.getIdImpositivo())
                .facturarExtranjero(domain.getFacturarExtranjero())
                .bloqueado(domain.getBloqueado())
                .discapacitado(domain.getDiscapacitado())
                .build();
    }

}
