package eterea.core.service.hexagonal.invoicedata.infrastructure.mapper;

import eterea.core.service.hexagonal.invoicedata.infrastructure.dto.ComprobanteAfipInvoiceDataResponse;
import eterea.core.service.kotlin.model.ComprobanteAfip;
import org.springframework.stereotype.Component;

@Component
public class ComprobanteAfipMapper {

    public ComprobanteAfipInvoiceDataResponse toResponse(ComprobanteAfip comprobanteAfip) {
        if (comprobanteAfip == null) {
            return null;
        }
        return ComprobanteAfipInvoiceDataResponse.builder()
                .comprobanteAfipId(comprobanteAfip.getComprobanteAfipId())
                .label(comprobanteAfip.getLabel())
                .build();
    }

}
