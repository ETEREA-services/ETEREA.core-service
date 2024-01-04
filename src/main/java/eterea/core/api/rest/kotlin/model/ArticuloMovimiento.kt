package eterea.core.api.rest.kotlin.model

import com.fasterxml.jackson.annotation.JsonFormat
import jakarta.persistence.*
import java.math.BigDecimal
import java.time.OffsetDateTime

@Entity
@Table(name = "detartic")
data class ArticuloMovimiento(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "clave")
    var articuloMovimientoId: Long? = null,

    @Column(name = "clavemovclie")
    var clienteMovimientoId: Long? = null,

    @Column(name = "clavemovstock")
    var stockMovimientoId: Long? = null,

    @Column(name = "clavemovtenencia")
    var tenenciaMovimientoId: Long? = null,

    @Column(name = "cgocentro")
    var centroStockId: Int? = null,

    @Column(name = "cgocomprob")
    var comprobanteId: Int? = null,

    @Column(name = "item")
    var item: Int? = 0,

    @Column(name = "cgoartic")
    var articuloId: String? = null,

    @Column(name = "dea_neg_id")
    var negocioId: Int? = null,

    @Column(name = "cantidad")
    var cantidad: BigDecimal? = BigDecimal.ZERO,

    @Column(name = "preciounitario")
    var precioUnitario: BigDecimal? = BigDecimal.ZERO,

    @Column(name = "preunitsiva")
    var precioUnitarioSinIva: BigDecimal? = BigDecimal.ZERO,

    @Column(name = "preunitciva")
    var precioUnitarioConIva: BigDecimal? = BigDecimal.ZERO,

    @Column(name = "cgocontable")
    var cuenta: Long? = null,

    @Column(name = "iva105")
    var iva105: Byte? = 0,

    @Column(name = "exento")
    var exento: Byte? = 0,

    @Column(name = "fecha")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
    var fechaMovimiento: OffsetDateTime? = null,

    @Column(name = "fechafac")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
    var fechaFactura: OffsetDateTime? = null,

    @Column(name = "dea_nivel")
    var nivel: Int? = 0,

    @Column(name = "dea_cic_id")
    var cierreCajaId: Long? = null,

    @Column(name = "dea_cir_id")
    var cierreRestaurantId: Long? = null,

    @Column(name = "dea_preciocompra")
    var precioCompra: BigDecimal? = BigDecimal.ZERO,

    @Column(name = "dea_preciovaluacion")
    var precioValuacion: BigDecimal? = BigDecimal.ZERO,

    @Column(name = "moz_id")
    var mozoId: Long? = null,

    @Column(name = "dea_comision")
    var comision: BigDecimal? = BigDecimal.ZERO,

    @Transient
    var total: BigDecimal? = BigDecimal.ZERO,

) : Auditable()
