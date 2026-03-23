package eterea.core.service.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import eterea.core.service.kotlin.model.Articulo;
import eterea.core.service.tool.Jsonifier;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@Table(name = "detartic")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArticuloMovimiento extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "clave")
    private Long articuloMovimientoId;

    @Column(name = "clavemovclie")
    private Long clienteMovimientoId;

    @Column(name = "clavemovstock")
    @Builder.Default
    private Long stockMovimientoId = 0L;

    @Column(name = "clavemovtenencia")
    @Builder.Default
    private Long tenenciaMovimientoId = 0L;

    @Column(name = "cgocentro")
    private Integer centroStockId;

    @Column(name = "cgocomprob")
    private Integer comprobanteId;

    @Column(name = "item")
    @Builder.Default
    private Integer item = 0;

    @Column(name = "cgoartic")
    private String articuloId;

    @Column(name = "dea_neg_id")
    private Integer negocioId;

    @Column(name = "cantidad")
    @Builder.Default
    private BigDecimal cantidad = BigDecimal.ZERO;

    @Column(name = "preciounitario")
    @Builder.Default
    private BigDecimal precioUnitario = BigDecimal.ZERO;

    @Column(name = "preunitsiva")
    @Builder.Default
    private BigDecimal precioUnitarioSinIva = BigDecimal.ZERO;

    @Column(name = "preunitciva")
    @Builder.Default
    private BigDecimal precioUnitarioConIva = BigDecimal.ZERO;

    @Column(name = "cgocontable")
    private Long numeroCuenta;

    @Column(name = "iva105")
    @Builder.Default
    private Byte iva105 = 0;

    @Column(name = "exento")
    @Builder.Default
    private Byte exento = 0;

    @Column(name = "fecha")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
    private OffsetDateTime fechaMovimiento;

    @Column(name = "fechafac")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
    private OffsetDateTime fechaFactura;

    @Column(name = "dea_nivel")
    @Builder.Default
    private Integer nivel = 0;

    @Column(name = "dea_cic_id")
    @Builder.Default
    private Long cierreCajaId = 0L;

    @Column(name = "dea_cir_id")
    @Builder.Default
    private Long cierreRestaurantId = 0L;

    @Column(name = "dea_preciocompra")
    @Builder.Default
    private BigDecimal precioCompra = BigDecimal.ZERO;

    @Column(name = "dea_preciovaluacion")
    @Builder.Default
    private BigDecimal precioValuacion = BigDecimal.ZERO;

    @Column(name = "moz_id")
    @Builder.Default
    private Long mozoId = 0L;

    @Column(name = "dea_comision")
    @Builder.Default
    private BigDecimal comision = BigDecimal.ZERO;

    private String trackUuid;

    @Transient
    @Builder.Default
    private BigDecimal total = BigDecimal.ZERO;

    @OneToOne(optional = true)
    @JoinColumn(name = "cgoartic", updatable = false, insertable = false)
    private Articulo articulo;

    public String jsonify() {
        return Jsonifier.builder(this).build();
    }

}
