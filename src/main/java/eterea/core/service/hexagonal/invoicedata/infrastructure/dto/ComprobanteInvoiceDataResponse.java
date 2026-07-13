package eterea.core.service.hexagonal.invoicedata.infrastructure.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ComprobanteInvoiceDataResponse {

    private String letraComprobante;
    private Byte contado;
    private ComprobanteAfipInvoiceDataResponse comprobanteAfip;

}
