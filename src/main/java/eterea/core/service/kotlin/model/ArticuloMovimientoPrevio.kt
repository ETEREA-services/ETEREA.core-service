package eterea.core.service.kotlin.model

import com.fasterxml.jackson.annotation.JsonFormat
import jakarta.persistence.*
import java.math.BigDecimal
import java.time.OffsetDateTime

@Entity
@Table(name = "articulomovimientoprevio")
data class ArticuloMovimientoPrevio(

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "articulomovimientoprevio_id")
    var articuloMovimientoPrevioId: Long? = null,

    @Column(name = "clientemovimientoprevio_id")
    var clienteMovimientoPrevioId: Long? = null,

    var articuloId: String? = null,
    var cantidad: BigDecimal = BigDecimal.ZERO,
    var precioUnitarioSinIva: BigDecimal = BigDecimal.ZERO,
    var precioUnitarioConIva: BigDecimal = BigDecimal.ZERO,
    var cuentaVenta: Long? = null,

    @Column(name = "iva_21")
    var iva21: Byte = 0,

    @Column(name = "iva_105")
    var iva105: Byte = 0,

    var exento: Byte = 0,

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
    var fechaMovimiento: OffsetDateTime? = null,

    @Column(name = "articulomovimiento_id")
    var articuloMovimientoId: Long? = null,

    @OneToOne(optional = true)
    @JoinColumn(name = "articuloId", insertable = false, updatable = false)
    var articulo: Articulo? = null,

    @OneToOne(optional = true)
    @JoinColumn(name = "cuentaVenta", insertable = false, updatable = false)
    var cuenta: Cuenta? = null

) : Auditable()
