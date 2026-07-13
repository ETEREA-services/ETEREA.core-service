package eterea.core.service.hexagonal.stock.stockmovimiento.infrastructure.persistence.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import eterea.core.service.model.Auditable;
import eterea.core.service.tool.Jsonifyable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Table(name = "movstock")
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StockMovimientoEntity extends Auditable implements Jsonifyable {

    @Id
    @Column(name = "clave")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stockMovimientoId;

    @Column(name = "cgocomprob")
    private Integer comprobanteId;

    @Column(name = "nrocompinterno")
    @Builder.Default
    private Long numeroComprobanteInterno = 0L;

    @Column(name = "mst_neg_id")
    private Integer negocioId;

    @Column(name = "mst_neg_id_desde")
    private Integer negocioIdDesde;

    @Column(name = "mst_cgocentrodesde")
    private Integer centroStockIdDesde;

    @Column(name = "mst_neg_id_hasta")
    private Integer negocioIdHasta;

    @Column(name = "mst_cgocentrohasta")
    private Integer centroStockIdHasta;

    @Column(name = "mst_cstidhnombre")
    @Builder.Default
    private String centroStockIdHastaNombre = "";

    @Column(name = "fechareg")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXX", timezone = "UTC")
    private OffsetDateTime fechaRegistro;

    @Column(name = "cgoprov")
    private Long proveedorId;

    @Column(name = "cgocli")
    private Long clienteId;

    private Long legajo;

    @Column(name = "fechacomprob")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXX", timezone = "UTC")
    private OffsetDateTime fechaComprobante;

    @Column(name = "cgocomprob2")
    private Integer comprobanteIdFactura;

    @Column(name = "prefijo")
    private Integer prefijoFactura;

    @Column(name = "nrocomprob")
    private Long numeroComprobanteFactura;

    @Builder.Default
    private BigDecimal importe = BigDecimal.ZERO;

    @Column(name = "letracomanda")
    private String letraComanda;

    @Column(name = "mst_observ")
    private String observaciones;

    @Column(name = "mst_cic_id")
    private Long cierreCajaId;

    @Column(name = "mst_cir_id")
    private Long cierreRestaurantId;

    @Column(name = "mst_nivel")
    @Builder.Default
    private Integer nivel = 0;

    @Column(name = "mst_fechacontable")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXX", timezone = "UTC")
    private OffsetDateTime fechaContable;

    @Column(name = "mst_nrocompcontable")
    private Integer ordenContable;

    @Column(name = "mst_neg_id_otro")
    private Integer negocioIdOtro;

    @Column(name = "mst_genauto")
    @Builder.Default
    private Byte generacionAutomatica = 0;

    @Column(name = "mst_pendiente")
    @Builder.Default
    private Byte pendiente = 0;

    @Column(name = "mst_rechazada")
    @Builder.Default
    private Byte rechazada = 0;

    @Builder.Default
    private Byte facturaProveedor = 0;

    @Builder.Default
    private BigDecimal netoFactura = BigDecimal.ZERO;

    @Builder.Default
    private BigDecimal netoRegistrado = BigDecimal.ZERO;

}
