package eterea.core.service.hexagonal.invoicedata.infrastructure.mapper;

import eterea.core.service.hexagonal.invoicedata.infrastructure.dto.RegistroCaeResponse;
import eterea.core.service.model.RegistroCae;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RegistroCaeMapper {

    private final ComprobanteMapper comprobanteMapper;

    public RegistroCaeResponse toResponse(RegistroCae registroCae) {
        if (registroCae == null) {
            return null;
        }
        return RegistroCaeResponse.builder()
                .tipoDocumento(registroCae.getTipoDocumento())
                .puntoVenta(registroCae.getPuntoVenta())
                .comprobanteId(registroCae.getComprobanteId())
                .numeroComprobante(registroCae.getNumeroComprobante())
                .total(registroCae.getTotal())
                .numeroDocumento(registroCae.getNumeroDocumento())
                .cae(registroCae.getCae())
                .caeVencimiento(registroCae.getCaeVencimiento())
                .fecha(registroCae.getFecha())
                .comprobante(comprobanteMapper.toResponse(registroCae.getComprobante()))
                .build();
    }

}
