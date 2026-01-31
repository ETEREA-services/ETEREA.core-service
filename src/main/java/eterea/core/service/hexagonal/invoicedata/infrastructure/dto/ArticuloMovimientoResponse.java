package eterea.core.service.hexagonal.invoicedata.infrastructure.dto;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class ArticuloMovimientoResponse {

    private BigDecimal cantidad;
    private BigDecimal precioUnitarioSinIva;
    private BigDecimal precioUnitarioConIva;
    private ArticuloResponse articulo;
    private ConceptoFacturadoResponse conceptoFacturado;

}
