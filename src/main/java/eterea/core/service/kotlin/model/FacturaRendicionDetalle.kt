package eterea.core.service.kotlin.model

import com.fasterxml.jackson.annotation.JsonFormat
import jakarta.persistence.*
import java.math.BigDecimal
import java.time.OffsetDateTime

@Entity
@Table(name = "factrenddet", uniqueConstraints = [UniqueConstraint(columnNames = ["frd_neg_id", "frd_frc_id", "frd_prv_id", "frd_cmp_id", "frd_prefijo", "frd_numero"])])
data class FacturaRendicionDetalle(

    @Id
    @Column(name = "clave")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var facturaRendicionDetalleId: Long? = null,

    @Column(name = "frd_neg_id")
    var negocioId: Int? = null,

    @Column(name = "frd_frc_id")
    var facturaRendicionCabeceraId: Long? = null,

    @Column(name = "frd_prv_id")
    var proveedorId: Long? = null,

    @Column(name = "frd_cmp_id")
    var comprobanteId: Int? = null,

    @Column(name = "frd_prefijo")
    var prefijo: Int = 0,

    @Column(name = "frd_numero")
    var numeroComprobante: Long = 0L,

    @Column(name = "frd_proveedor")
    var nombreProveedor: String = "",

    @Column(name = "frd_comprobante")
    var nombreComprobante: String = "",

    @Column(name = "frd_fecha")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
    var fechaComprobante: OffsetDateTime? = null,

    @Column(name = "frd_importe")
    var importe: BigDecimal = BigDecimal.ZERO,

    @Column(name = "frd_pendiente")
    var pendiente: Byte = 0

) : Auditable() {

    fun comprobanteKey(): String {
        return "$proveedorId.$comprobanteId.$prefijo.$numeroComprobante"
    }

}
