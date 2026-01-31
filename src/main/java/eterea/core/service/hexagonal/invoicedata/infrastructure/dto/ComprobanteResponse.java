package eterea.core.service.hexagonal.invoicedata.infrastructure.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ComprobanteResponse {

    private String letraComprobante;
    private Byte contado;
    private ComprobanteAfipResponse comprobanteAfip;

}
