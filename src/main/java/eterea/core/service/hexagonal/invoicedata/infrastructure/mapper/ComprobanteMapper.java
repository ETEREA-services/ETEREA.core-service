package eterea.core.service.hexagonal.invoicedata.infrastructure.mapper;

import eterea.core.service.hexagonal.invoicedata.infrastructure.dto.ComprobanteResponse;
import eterea.core.service.kotlin.model.Comprobante;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ComprobanteMapper {

    private final ComprobanteAfipMapper comprobanteAfipMapper;

    public ComprobanteResponse toResponse(Comprobante comprobante) {
        if (comprobante == null) {
            return null;
        }
        return ComprobanteResponse.builder()
                .letraComprobante(comprobante.getLetraComprobante())
                .contado(comprobante.getContado())
                .comprobanteAfip(comprobanteAfipMapper.toResponse(comprobante.getComprobanteAfip()))
                .build();
    }

}
