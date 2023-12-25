package eterea.core.api.rest.kotlin.model

import com.fasterxml.jackson.annotation.JsonFormat
import eterea.core.api.rest.model.Comprobante
import jakarta.persistence.*
import java.math.BigDecimal
import java.time.OffsetDateTime

@Entity
@Table(name = "movcon", uniqueConstraints = [UniqueConstraint(columnNames = ["fecha", "nrocomp", "item"])])
data class CuentaMovimiento(

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var cuentaMovimientoId: Long? = null,

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
    var fecha: OffsetDateTime? = null,

    @Column(name = "nrocomp")
    var orden: Int? = null,

    var item: Int? = null,
    var debita: Byte = 0,

    @Column(name = "mco_neg_id")
    var negocioId: Int? = null,

    @Column(name = "cuenta")
    var numeroCuenta: Long? = null,

    @Column(name = "cgotcomp")
    var comprobanteId: Int? = null,

    var concepto: String? = null,
    var importe: BigDecimal = BigDecimal.ZERO,

    @Column(name = "cgosub")
    var subrubroId: Long? = null,

    @Column(name = "cgoprov")
    var proveedorId: Long? = null,

    @Column(name = "cgoclie")
    var clienteId: Long? = null,

    @Column(name = "mco_cic_id")
    var cierreCajaId: Long? = null,

    @Column(name = "mco_nivel")
    var nivel: Int? = null,

    @Column(name = "mco_mcf_firma")
    var firma: Long? = null,

    @Column(name = "mco_tas_id")
    var tipoAsientoId: Int? = null,

    @Column(name = "articulomovimiento_id")
    var articuloMovimientoId: Long? = null,

    var ejercicioId: Int? = null,
    var inflacion: Byte? = null,

    @OneToOne(optional = true)
    @JoinColumn(name = "cuenta", insertable = false, updatable = false)
    var cuenta: Cuenta? = null,

    @OneToOne(optional = true)
    @JoinColumn(name = "cgotcomp", insertable = false, updatable = false)
    var comprobante: Comprobante? = null,

    @OneToOne(optional = true)
    @JoinColumn(name = "mco_neg_id", insertable = false, updatable = false)
    var negocio: Negocio? = null

) : Auditable()
