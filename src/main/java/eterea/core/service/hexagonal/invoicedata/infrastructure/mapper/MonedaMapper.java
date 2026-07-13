package eterea.core.service.hexagonal.invoicedata.infrastructure.mapper;

import eterea.core.service.hexagonal.invoicedata.infrastructure.dto.MonedaInvoiceDataResponse;
import eterea.core.service.kotlin.model.Moneda;
import org.springframework.stereotype.Component;

@Component
public class MonedaMapper {

    public MonedaInvoiceDataResponse toResponse(Moneda moneda) {
        if (moneda == null) {
            return null;
        }
        return MonedaInvoiceDataResponse.builder()
                .simbolo(moneda.getSimbolo())
                .build();
    }

}
