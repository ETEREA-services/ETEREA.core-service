package eterea.core.service.hexagonal.reservas.reservaarticulo.domain.model;

import eterea.core.service.hexagonal.stock.articulo.domain.model.Articulo;
import eterea.core.service.tool.Jsonifyable;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservaArticulo implements Jsonifyable {

    private Long reservaArticuloId;
    private Integer negocioId;
    private Long reservaId;
    private Long voucherId;
    private String articuloId;

    @Builder.Default
    private Integer cantidad = 0;

    @Builder.Default
    private BigDecimal comision = BigDecimal.ZERO;

    @Builder.Default
    private BigDecimal precioUnitarioSinComision = BigDecimal.ZERO;

    @Builder.Default
    private BigDecimal precioUnitario = BigDecimal.ZERO;

    @Builder.Default
    private BigDecimal precioUnitarioSinDescuento = BigDecimal.ZERO;

    @Builder.Default
    private BigDecimal precioCompra = BigDecimal.ZERO;

    @Builder.Default
    private String observaciones = "";

    private String trackUuid;

    private Articulo articulo;

}
