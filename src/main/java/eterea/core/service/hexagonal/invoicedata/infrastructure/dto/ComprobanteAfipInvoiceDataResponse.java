package eterea.core.service.hexagonal.invoicedata.infrastructure.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ComprobanteAfipInvoiceDataResponse {

    private Integer comprobanteAfipId;
    private String label;

}
