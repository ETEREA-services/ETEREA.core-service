package eterea.core.service.hexagonal.stock.articulomovimiento.infrastructure.web.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArticuloMovimientoResponse {
    private Long articuloMovimientoId;
    private Long clienteMovimientoId;
    private Long stockMovimientoId;
    private Integer centroStockId;
    private Integer comprobanteId;
    private Integer item;
    private String articuloId;
    private Integer negocioId;
    private BigDecimal cantidad;
    private BigDecimal precioUnitario;
    private BigDecimal precioUnitarioSinIva;
    private BigDecimal precioUnitarioConIva;
    private OffsetDateTime fechaMovimiento;
    private BigDecimal totalConIva;
    private BigDecimal totalSinIva;
}
