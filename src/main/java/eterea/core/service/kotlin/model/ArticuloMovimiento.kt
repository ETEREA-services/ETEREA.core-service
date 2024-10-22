package eterea.core.service.kotlin.model

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
    var stockMovimientoId: Long = 0,

    @Column(name = "clavemovtenencia")
    var tenenciaMovimientoId: Long = 0,

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
    var numeroCuenta: Long? = null,

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
    var cierreCajaId: Long = 0,

    @Column(name = "dea_cir_id")
    var cierreRestaurantId: Long = 0,

    @Column(name = "dea_preciocompra")
    var precioCompra: BigDecimal? = BigDecimal.ZERO,

    @Column(name = "dea_preciovaluacion")
    var precioValuacion: BigDecimal? = BigDecimal.ZERO,

    @Column(name = "moz_id")
    var mozoId: Long = 0,

    @Column(name = "dea_comision")
    var comision: BigDecimal? = BigDecimal.ZERO,

    @Transient
    var total: BigDecimal? = BigDecimal.ZERO,

    @OneToOne(optional = true)
    @JoinColumn(name = "cgoartic", updatable = false, insertable = false)
    var articulo: Articulo? = null

) : Auditable() {

    class Builder {
        var articuloMovimientoId: Long? = null
        var clienteMovimientoId: Long? = null
        var stockMovimientoId: Long = 0
        var tenenciaMovimientoId: Long = 0
        var centroStockId: Int? = null
        var comprobanteId: Int? = null
        var item: Int? = 0
        var articuloId: String? = null
        var negocioId: Int? = null
        var cantidad: BigDecimal? = BigDecimal.ZERO
        var precioUnitario: BigDecimal? = BigDecimal.ZERO
        var precioUnitarioSinIva: BigDecimal? = BigDecimal.ZERO
        var precioUnitarioConIva: BigDecimal? = BigDecimal.ZERO
        var numeroCuenta: Long? = null
        var iva105: Byte? = 0
        var exento: Byte? = 0
        var fechaMovimiento: OffsetDateTime? = null
        var fechaFactura: OffsetDateTime? = null
        var nivel: Int? = 0
        var cierreCajaId: Long = 0
        var cierreRestaurantId: Long = 0
        var precioCompra: BigDecimal? = BigDecimal.ZERO
        var precioValuacion: BigDecimal? = BigDecimal.ZERO
        var mozoId: Long = 0
        var comision: BigDecimal? = BigDecimal.ZERO
        var total: BigDecimal? = BigDecimal.ZERO
        var articulo: Articulo? = null

        fun articuloMovimientoId(articuloMovimientoId: Long?) =
            apply { this.articuloMovimientoId = articuloMovimientoId }

        fun clienteMovimientoId(clienteMovimientoId: Long?) = apply { this.clienteMovimientoId = clienteMovimientoId }
        fun stockMovimientoId(stockMovimientoId: Long) = apply { this.stockMovimientoId = stockMovimientoId }
        fun tenenciaMovimientoId(tenenciaMovimientoId: Long) =
            apply { this.tenenciaMovimientoId = tenenciaMovimientoId }

        fun centroStockId(centroStockId: Int?) = apply { this.centroStockId = centroStockId }
        fun comprobanteId(comprobanteId: Int?) = apply { this.comprobanteId = comprobanteId }
        fun item(item: Int?) = apply { this.item = item }
        fun articuloId(articuloId: String?) = apply { this.articuloId = articuloId }
        fun negocioId(negocioId: Int?) = apply { this.negocioId = negocioId }
        fun cantidad(cantidad: BigDecimal?) = apply { this.cantidad = cantidad }
        fun precioUnitario(precioUnitario: BigDecimal?) = apply { this.precioUnitario = precioUnitario }
        fun precioUnitarioSinIva(precioUnitarioSinIva: BigDecimal?) =
            apply { this.precioUnitarioSinIva = precioUnitarioSinIva }

        fun precioUnitarioConIva(precioUnitarioConIva: BigDecimal?) =
            apply { this.precioUnitarioConIva = precioUnitarioConIva }

        fun numeroCuenta(numeroCuenta: Long?) = apply { this.numeroCuenta = numeroCuenta }
        fun iva105(iva105: Byte?) = apply { this.iva105 = iva105 }
        fun exento(exento: Byte?) = apply { this.exento = exento }
        fun fechaMovimiento(fechaMovimiento: OffsetDateTime?) = apply { this.fechaMovimiento = fechaMovimiento }
        fun fechaFactura(fechaFactura: OffsetDateTime?) = apply { this.fechaFactura = fechaFactura }
        fun nivel(nivel: Int?) = apply { this.nivel = nivel }
        fun cierreCajaId(cierreCajaId: Long) = apply { this.cierreCajaId = cierreCajaId }
        fun cierreRestaurantId(cierreRestaurantId: Long) = apply { this.cierreRestaurantId = cierreRestaurantId }
        fun precioCompra(precioCompra: BigDecimal?) = apply { this.precioCompra = precioCompra }
        fun precioValuacion(precioValuacion: BigDecimal?) = apply { this.precioValuacion = precioValuacion }
        fun mozoId(mozoId: Long) = apply { this.mozoId = mozoId }
        fun comision(comision: BigDecimal?) = apply { this.comision = comision }
        fun total(total: BigDecimal?) = apply { this.total = total }
        fun articulo(articulo: Articulo?) = apply { this.articulo = articulo }

        fun build() = ArticuloMovimiento(
            articuloMovimientoId, clienteMovimientoId, stockMovimientoId, tenenciaMovimientoId, centroStockId,
            comprobanteId, item, articuloId, negocioId, cantidad, precioUnitario, precioUnitarioSinIva,
            precioUnitarioConIva, numeroCuenta, iva105, exento, fechaMovimiento, fechaFactura, nivel, cierreCajaId,
            cierreRestaurantId, precioCompra, precioValuacion, mozoId, comision, total, articulo
        )
    }
}

