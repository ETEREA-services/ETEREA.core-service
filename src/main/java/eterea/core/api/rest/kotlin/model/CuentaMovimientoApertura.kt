package eterea.core.api.rest.kotlin.model

import com.fasterxml.jackson.annotation.JsonFormat
import jakarta.persistence.*
import java.math.BigDecimal
import java.time.OffsetDateTime

@Entity
@Table(
    name = "movconapertura",
    uniqueConstraints = [UniqueConstraint(columnNames = ["mca_fecha", "mca_nrocomp", "mca_item"])]
)
data class CuentaMovimientoApertura(

    @Column(name = "mca_fecha")
    @JsonFormat(
        shape = JsonFormat.Shape.STRING,
        pattern = "yyyy-MM-dd'T'HH:mm:ssZ",
        timezone = "UTC"
    )
    var fecha: OffsetDateTime? = null,

    @Column(name = "mca_nrocomp")
    var orden: Int? = null,

    @Column(name = "mca_item")
    var item: Int? = null,

    @Column(name = "mca_cuenta")
    var numeroCuenta: Long? = null,

    @Column(name = "mca_concepto")
    var concepto: String? = null,

    @Column(name = "mca_neg_id")
    var negocioId: Int? = null,

    @Column(name = "mca_debita")
    var debita: Byte? = null,

    @Column(name = "mca_importe")
    var importe: BigDecimal = BigDecimal.ZERO,

    @Id
    @Column(name = "clave")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var cuentaMovimientoAperturaId: Long? = null

) : Auditable()
