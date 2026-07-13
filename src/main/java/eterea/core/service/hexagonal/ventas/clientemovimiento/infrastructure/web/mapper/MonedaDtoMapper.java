package eterea.core.service.hexagonal.ventas.clientemovimiento.infrastructure.web.mapper;

import eterea.core.service.kotlin.model.Moneda;
import eterea.core.service.hexagonal.ventas.clientemovimiento.infrastructure.web.dto.MonedaResponse;
import org.springframework.stereotype.Component;

@Component
public class MonedaDtoMapper {

    public MonedaResponse toResponse(Moneda domain) {
        if (domain == null) {
            return null;
        }
        return MonedaResponse.builder()
                .monedaId(domain.getMonedaId())
                .nombre(domain.getNombre())
                .simbolo(domain.getSimbolo())
                .valorId(domain.getValorId())
                .build();
    }

}
