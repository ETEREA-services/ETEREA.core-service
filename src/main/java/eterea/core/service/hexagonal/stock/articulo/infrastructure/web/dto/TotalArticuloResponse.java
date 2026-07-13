package eterea.core.service.hexagonal.stock.articulo.infrastructure.web.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TotalArticuloResponse {

    private String articuloId;
    private BigDecimal cantidad;
    private BigDecimal precioUnitarioSinIva;
    private BigDecimal precioUnitarioConIva;
    private BigDecimal precioTotalSinIva;
    private BigDecimal precioTotalConIva;
    private Byte iva105;
    private Byte exento;

}
