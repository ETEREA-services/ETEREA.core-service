package eterea.core.service.hexagonal.invoicedata.infrastructure.mapper;

import eterea.core.service.hexagonal.comprobante.domain.model.Comprobante;
import eterea.core.service.hexagonal.invoicedata.infrastructure.dto.ComprobanteInvoiceDataResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ComprobanteResponseMapper {

    private final ComprobanteAfipMapper comprobanteAfipMapper;

    public ComprobanteInvoiceDataResponse toResponse(Comprobante comprobante) {
        if (comprobante == null) {
            return null;
        }
        return ComprobanteInvoiceDataResponse.builder()
                .letraComprobante(comprobante.getLetraComprobante())
                .contado(comprobante.getContado())
                .comprobanteAfip(comprobanteAfipMapper.toResponse(comprobante.getComprobanteAfip()))
                .build();
    }

}
