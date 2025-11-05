package eterea.core.service.kotlin.model

import com.fasterxml.jackson.annotation.JsonFormat
import eterea.core.service.model.Auditable
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import java.math.BigDecimal
import java.time.OffsetDateTime

@Entity
@Table(name = "movprov")
data class ProveedorMovimiento(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "clave")
    var proveedorMovimientoId: Long? = null,

    @Column(name = "mpr_emp_id")
    var empresaId: Int? = null,

    @Column(name = "mpr_neg_id")
    var negocioId: Int? = null,

    @Column(name = "cgoprov")
    var proveedorId: Long? = null,

    @Column(name = "cgocomprob")
    var comprobanteId: Int? = null,

    @Column(name = "fechacomprob")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
    var fechaComprobante: OffsetDateTime? = null,

    @Column(name = "mpr_fechavenc")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
    var fechaVencimiento: OffsetDateTime? = null,

    var prefijo: Int = 0,

    @Column(name = "nrocomprob")
    var numeroComprobante: Long = 0L,

    var importe: BigDecimal = BigDecimal.ZERO,
    var cancelado: BigDecimal = BigDecimal.ZERO,
    var aplicado: BigDecimal = BigDecimal.ZERO,
    var neto: BigDecimal = BigDecimal.ZERO,

    @Column(name = "netocancelado")
    var netoCancelado: BigDecimal = BigDecimal.ZERO,

    @Column(name = "montoiva")
    var montoIva: BigDecimal = BigDecimal.ZERO,

    @Column(name = "montoiva27")
    var montoIva27: BigDecimal = BigDecimal.ZERO,

    @Column(name = "montoiva105")
    var montoIva105: BigDecimal = BigDecimal.ZERO,

    @Column(name = "perciva")
    var percepcionIva: BigDecimal = BigDecimal.ZERO,

    @Column(name = "percingbrutos")
    var percepcionIngresosBrutos: BigDecimal = BigDecimal.ZERO,

    @Column(name = "gng")
    var gastosNoGravados: BigDecimal = BigDecimal.ZERO,

    var ajustes: BigDecimal = BigDecimal.ZERO,

    @Column(name = "fechareg")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
    var fechaContable: OffsetDateTime? = null,

    @Column(name = "nrocompconta")
    var ordenContable: Int? = null,

    @Column(name = "montofactc")
    var montoFacturadoC: BigDecimal = BigDecimal.ZERO,

    @Column(name = "montosujetoretib")
    var montoSujetoRetencionesIIBB: BigDecimal = BigDecimal.ZERO,

    @Column(name = "montoretib")
    var montoRetencionesIIBB: BigDecimal = BigDecimal.ZERO,

    @Column(name = "cgoretib")
    var codigoRetencionesIIBB: Long? = null,

    @Column(name = "nrocompretib")
    var numeroComprobanteRetencionesIIBB: Long? = null,

    var concepto: String = "",

    @Column(name = "mpr_cic_id")
    var cierreCajaId: Long? = null,

    @Column(name = "mpr_nivel")
    var nivel: Int? = null,

    @Column(name = "mpr_neg_id_paga")
    var negocioIdPaga: Int? = null,

    @Column(name = "mpr_concursada")
    var concursada: Byte = 0,

    @Column(name = "mpr_importeconcursado")
    var importeConcursado: BigDecimal = BigDecimal.ZERO,

    @Column(name = "mpr_fechaconcurso")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
    var fechaContableConcurso: OffsetDateTime? = null,

    @Column(name = "mpr_nrocompconcurso")
    var ordenContableConcurso: Int? = null,

    @Column(name = "mpr_marca")
    var marca: Byte = 0,

    @Column(name = "mpr_orden")
    var orden: Int = 0,

    @Column(name = "mpr_transferida")
    var transferida: Byte = 0,

    @OneToOne(optional = true)
    @JoinColumn(name = "cgocomprob", insertable = false, updatable = false)
    var comprobante: Comprobante? = null,

    @OneToOne(optional = true)
    @JoinColumn(name = "cgoprov", insertable = false, updatable = false)
    var proveedor: Proveedor? = null

) : Auditable() {

    fun comprobanteKey() : String {
        return "$proveedorId.$comprobanteId.$prefijo.$numeroComprobante"
    }

}
