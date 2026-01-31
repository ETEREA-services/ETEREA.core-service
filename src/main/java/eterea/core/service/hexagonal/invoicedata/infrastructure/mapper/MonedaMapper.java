package eterea.core.service.hexagonal.invoicedata.infrastructure.mapper;

import eterea.core.service.hexagonal.invoicedata.infrastructure.dto.MonedaResponse;
import eterea.core.service.kotlin.model.Moneda;
import org.springframework.stereotype.Component;

@Component
public class MonedaMapper {

    public MonedaResponse toResponse(Moneda moneda) {
        if (moneda == null) {
            return null;
        }
        return MonedaResponse.builder()
                .simbolo(moneda.getSimbolo())
                .build();
    }

}
