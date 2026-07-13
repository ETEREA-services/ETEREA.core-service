package eterea.core.service.hexagonal.stock.stockmovimiento.infrastructure.web.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StockMovimientoResponse {

    private Long stockMovimientoId;
    private Integer comprobanteId;
    private Long numeroComprobanteInterno;
    private Integer negocioId;
    private Integer negocioIdDesde;
    private Integer centroStockIdDesde;
    private Integer negocioIdHasta;
    private Integer centroStockIdHasta;
    private String centroStockIdHastaNombre;
    private OffsetDateTime fechaRegistro;
    private Long proveedorId;
    private Long clienteId;
    private Long legajo;
    private OffsetDateTime fechaComprobante;
    private Integer comprobanteIdFactura;
    private Integer prefijoFactura;
    private Long numeroComprobanteFactura;
    private BigDecimal importe;
    private String letraComanda;
    private String observaciones;
    private Long cierreCajaId;
    private Long cierreRestaurantId;
    private Integer nivel;
    private OffsetDateTime fechaContable;
    private Integer ordenContable;
    private Integer negocioIdOtro;
    private Byte generacionAutomatica;
    private Byte pendiente;
    private Byte rechazada;
    private Byte facturaProveedor;
    private BigDecimal netoFactura;
    private BigDecimal netoRegistrado;

}
