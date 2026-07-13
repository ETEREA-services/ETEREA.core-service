package eterea.core.service.hexagonal.invoicedata.infrastructure.mapper;

import eterea.core.service.hexagonal.comprobante.infrastructure.persistence.mapper.ComprobanteMapper;
import eterea.core.service.hexagonal.comprobante.infrastructure.web.mapper.ComprobanteDtoMapper;
import eterea.core.service.hexagonal.invoicedata.infrastructure.dto.RegistroCaeInvoiceDataResponse;
import eterea.core.service.model.RegistroCae;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RegistroCaeMapper {

    private final ComprobanteResponseMapper comprobanteResponseMapper;
    private final ComprobanteDtoMapper comprobanteDtoMapper;
    private final ComprobanteMapper comprobanteMapper;

    public RegistroCaeInvoiceDataResponse toResponse(RegistroCae registroCae) {
        if (registroCae == null) {
            return null;
        }
        return RegistroCaeInvoiceDataResponse.builder()
                .tipoDocumento(registroCae.getTipoDocumento())
                .puntoVenta(registroCae.getPuntoVenta())
                .comprobanteId(registroCae.getComprobanteId())
                .numeroComprobante(registroCae.getNumeroComprobante())
                .total(registroCae.getTotal())
                .numeroDocumento(registroCae.getNumeroDocumento())
                .cae(registroCae.getCae())
                .caeVencimiento(registroCae.getCaeVencimiento())
                .fecha(registroCae.getFecha())
                .comprobante(comprobanteResponseMapper.toResponse(comprobanteMapper.toDomain(registroCae.getComprobante())))
                .build();
    }

}
