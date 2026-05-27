package eterea.core.service.hexagonal.articulo.infrastructure.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import eterea.core.service.hexagonal.cuenta.infrastructure.persistence.entity.CuentaEntity;
import eterea.core.service.model.Auditable;
import eterea.core.service.tool.Jsonifier;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "articulos")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ArticuloEntity extends Auditable {

    @Id
    @Column(name = "codigo")
    private String articuloId;

    @Column(name = "art_neg_id")
    private Integer negocioId;

    @Builder.Default
    private String descripcion = "";

    @Builder.Default
    @Column(name = "art_voucher")
    private String leyendaVoucher = "";

    @Builder.Default
    @Column(name = "precioventasiniva")
    private BigDecimal precioVentaSinIva = BigDecimal.ZERO;

    @Builder.Default
    @Column(name = "preciounitario")
    private BigDecimal precioVentaConIva = BigDecimal.ZERO;

    @Column(name = "cgocontable")
    private Long numeroCuentaVentas;

    @Column(name = "cgocontablecompras")
    private Long numeroCuentaCompras;

    @Column(name = "cgocontablegastos")
    private Long numeroCuentaGastos;

    @Column(name = "cgocentro")
    private Integer centroStockId;

    @Column(name = "cgorubro")
    private Long rubroId;

    @Column(name = "cgosubrubro")
    private Long subRubroId;

    @Builder.Default
    @Column(name = "preciocompra")
    private BigDecimal precioCompra = BigDecimal.ZERO;

    @Builder.Default
    private byte iva105 = 0;

    @Builder.Default
    @Column(name = "art_compraneto")
    private BigDecimal precioCompraNeto = BigDecimal.ZERO;

    @Builder.Default
    private byte exento = 0;

    @Builder.Default
    @Column(name = "stockminimo")
    private long stockMinimo = 0L;

    @Builder.Default
    @Column(name = "stockoptimo")
    private long stockOptimo = 0L;

    @Builder.Default
    @Column(name = "bloqueocompras")
    private byte bloqueoCompras = 0;

    @Builder.Default
    @Column(name = "bloqueostock")
    private byte bloqueoStock = 0;

    @Builder.Default
    @Column(name = "bloqueoventas")
    private byte bloqueoVentas = 0;

    @Column(name = "art_ume_id")
    private Long unidadMedidaId;

    @Builder.Default
    @Column(name = "art_condecimales")
    private byte conDecimales = 0;

    @Builder.Default
    @Column(name = "art_ventas")
    private byte ventas = 0;

    @Builder.Default
    @Column(name = "art_compras")
    private byte compras = 0;

    @Builder.Default
    @Column(name = "umedida")
    private String unidadMedida = "";

    @Column(name = "art_con_id")
    private Integer conversionId;

    @Builder.Default
    @Column(name = "art_ventasinstock")
    private byte ventaSinStock = 0;

    @Builder.Default
    @Column(name = "art_controlstock")
    private byte controlaStock = 0;

    @Builder.Default
    @Column(name = "art_asientocostos")
    private byte asientoCostos = 0;

    @Builder.Default
    @Column(name = "art_maskbal")
    private String mascaraBalanza = "";

    @Builder.Default
    @Column(name = "art_habingreso")
    private byte habilitaIngreso = 0;

    @Builder.Default
    @Column(name = "art_comision")
    private BigDecimal comision = BigDecimal.ZERO;

    @Column(name = "art_pre_ID")
    private Integer prestadorId;

    @Column(name = "clave")
    private Long autoNumericoId;

    @OneToOne(optional = true)
    @JoinColumn(name = "cgocontable", insertable = false, updatable = false)
    private CuentaEntity cuentaVentas;

    public String jsonify() {
        return Jsonifier.builder(this).build();
    }
}
