package eterea.core.service.hexagonal.proveedormovimiento.infrastructure.persistence.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import eterea.core.service.kotlin.model.Comprobante;
import eterea.core.service.kotlin.model.Negocio;
import eterea.core.service.kotlin.model.Proveedor;
import eterea.core.service.model.Auditable;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Table(name = "movprov")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProveedorMovimientoEntity extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "clave")
    private Long proveedorMovimientoId;

    @Column(name = "mpr_emp_id")
    private Integer empresaId;

    @Column(name = "mpr_neg_id")
    private Integer negocioId;

    @Column(name = "cgoprov")
    private Long proveedorId;

    @Column(name = "cgocomprob")
    private Integer comprobanteId;

    @Column(name = "fechacomprob")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
    private OffsetDateTime fechaComprobante;

    @Column(name = "mpr_fechavenc")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
    private OffsetDateTime fechaVencimiento;

    @Builder.Default
    private Integer prefijo = 0;

    @Builder.Default
    @Column(name = "nrocomprob")
    private Long numeroComprobante = 0L;

    @Builder.Default
    private BigDecimal importe = BigDecimal.ZERO;
    @Builder.Default
    private BigDecimal cancelado = BigDecimal.ZERO;
    @Builder.Default
    private BigDecimal aplicado = BigDecimal.ZERO;
    @Builder.Default
    private BigDecimal neto = BigDecimal.ZERO;

    @Builder.Default
    @Column(name = "netocancelado")
    private BigDecimal netoCancelado = BigDecimal.ZERO;

    @Builder.Default
    @Column(name = "montoiva")
    private BigDecimal montoIva = BigDecimal.ZERO;

    @Builder.Default
    @Column(name = "montoiva27")
    private BigDecimal montoIva27 = BigDecimal.ZERO;

    @Builder.Default
    @Column(name = "montoiva105")
    private BigDecimal montoIva105 = BigDecimal.ZERO;

    @Builder.Default
    @Column(name = "perciva")
    private BigDecimal percepcionIva = BigDecimal.ZERO;

    @Builder.Default
    @Column(name = "percingbrutos")
    private BigDecimal percepcionIngresosBrutos = BigDecimal.ZERO;

    @Builder.Default
    @Column(name = "gng")
    private BigDecimal gastosNoGravados = BigDecimal.ZERO;

    @Builder.Default
    private BigDecimal ajustes = BigDecimal.ZERO;

    @Column(name = "fechareg")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
    private OffsetDateTime fechaContable;

    @Column(name = "nrocompconta")
    private Integer ordenContable;

    @Builder.Default
    @Column(name = "montofactc")
    private BigDecimal montoFacturadoC = BigDecimal.ZERO;

    @Builder.Default
    @Column(name = "montosujetoretib")
    private BigDecimal montoSujetoRetencionesIIBB = BigDecimal.ZERO;

    @Builder.Default
    @Column(name = "montoretib")
    private BigDecimal montoRetencionesIIBB = BigDecimal.ZERO;

    @Column(name = "cgoretib")
    private Long codigoRetencionesIIBB;

    @Column(name = "nrocompretib")
    private Long numeroComprobanteRetencionesIIBB;

    @Builder.Default
    private String concepto = "";

    @Column(name = "mpr_cic_id")
    private Long cierreCajaId;

    @Column(name = "mpr_nivel")
    private Integer nivel;

    @Column(name = "mpr_neg_id_paga")
    private Integer negocioIdPaga;

    @Builder.Default
    @Column(name = "mpr_concursada")
    private Byte concursada = 0;

    @Builder.Default
    @Column(name = "mpr_importeconcursado")
    private BigDecimal importeConcursado = BigDecimal.ZERO;

    @Column(name = "mpr_fechaconcurso")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
    private OffsetDateTime fechaContableConcurso;

    @Column(name = "mpr_nrocompconcurso")
    private Integer ordenContableConcurso;

    @Builder.Default
    @Column(name = "mpr_marca")
    private Byte marca = 0;

    @Builder.Default
    @Column(name = "mpr_orden")
    private Integer orden = 0;

    @Builder.Default
    @Column(name = "mpr_transferida")
    private Byte transferida = 0;

    @OneToOne(optional = true)
    @JoinColumn(name = "cgocomprob", insertable = false, updatable = false)
    private Comprobante comprobante;

    @OneToOne(optional = true)
    @JoinColumn(name = "cgoprov", insertable = false, updatable = false)
    private Proveedor proveedor;

    @OneToOne(optional = true)
    @JoinColumn(name = "mpr_neg_id", insertable = false, updatable = false)
    private Negocio negocio;

    public String comprobanteKey() {
        return proveedorId + "." + comprobanteId + "." + prefijo + "." + numeroComprobante;
    }

}
