package eterea.core.service.hexagonal.invoicedata.infrastructure.dto;

import eterea.core.service.hexagonal.stock.articulo.infrastructure.web.dto.ArticuloResponseForInvoiceData;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class ArticuloMovimientoInvoiceDataResponse {

    private BigDecimal cantidad;
    private BigDecimal precioUnitarioSinIva;
    private BigDecimal precioUnitarioConIva;
    private BigDecimal precioTotalSinIva;
    private BigDecimal precioTotalConIva;
    private ArticuloResponseForInvoiceData articulo;
    private ConceptoFacturadoInvoiceDataResponse conceptoFacturado;

}
