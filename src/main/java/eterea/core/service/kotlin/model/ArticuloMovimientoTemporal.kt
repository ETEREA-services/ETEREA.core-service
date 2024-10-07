package eterea.core.service.kotlin.model

import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "tmdetartic")
data class ArticuloMovimientoTemporal(

    @Id
    @Column(name = "clave")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var articuloMovimientoTemporalId: Long? = null,

    @Column(name = "ipaddress")
    var ipAddress: String = "",

    @Column(name = "dat_hwnd")
    var hwnd: Long = 0,

    var item: Long = 0,
    var item2: Int = 0,

    @Column(name = "cgocentro")
    var centroId: Long? = null,

    @Column(name = "cgoarticulo")
    var articuloId: String? = null,

    @Column(name = "clavedetartic")
    var articuloMovimientoId: Long? = null,

    var descripcion: String? = null,

    @Column(name = "preciounitario")
    var precioUnitario: BigDecimal = BigDecimal.ZERO,

    var cantidad: BigDecimal = BigDecimal.ZERO,
    var total: BigDecimal = BigDecimal.ZERO,

    @Column(name = "cgocontable")
    var cuenta: Long? = null,

    @Column(name = "preunitciva")
    var precioUnitarioConIva: BigDecimal = BigDecimal.ZERO,

    @Column(name = "preunitsiva")
    var precioUnitarioSinIva: BigDecimal = BigDecimal.ZERO,

    @Column(name = "preciocompra")
    var precioCompra: BigDecimal = BigDecimal.ZERO,

    var iva105: Byte = 0,
    var exento: Byte = 0,
    var comision: BigDecimal = BigDecimal.ZERO

) : Auditable()
