package eterea.core.service.hexagonal.stock.articulomovimiento.domain.model;

import eterea.core.service.hexagonal.stock.articulo.domain.model.Articulo;
import eterea.core.service.tool.Jsonifier;
import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArticuloMovimiento {

    private Long articuloMovimientoId;
    private Long clienteMovimientoId;
    private Long stockMovimientoId;
    private Long tenenciaMovimientoId;
    private Integer centroStockId;
    private Integer comprobanteId;
    private Integer item;
    private String articuloId;
    private Integer negocioId;
    private BigDecimal cantidad;
    private BigDecimal precioUnitario;
    private BigDecimal precioUnitarioSinIva;
    private BigDecimal precioUnitarioConIva;
    private Long numeroCuenta;
    private Byte iva105;
    private Byte exento;
    private OffsetDateTime fechaMovimiento;
    private OffsetDateTime fechaFactura;
    private Integer nivel;
    private Long cierreCajaId;
    private Long cierreRestaurantId;
    private BigDecimal precioCompra;
    private BigDecimal precioValuacion;
    private Long mozoId;
    private BigDecimal comision;
    private String trackUuid;
    private BigDecimal totalConIva;
    private BigDecimal totalSinIva;
    private Articulo articulo;

    public String jsonify() {
        return Jsonifier.builder(this).build();
    }
}
