package eterea.core.service.kotlin.model

import com.fasterxml.jackson.annotation.JsonFormat
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToOne
import java.math.BigDecimal
import java.time.OffsetDateTime

@Entity
data class Inventario(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "clave")
    var inventarioId: Long? = null,

    @Column(name = "inv_fecha")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
    var fecha: OffsetDateTime? = null,

    @Column(name = "inv_int_id")
    var inventarioTurnoId: Int? = null,

    @Column(name = "inv_cst_id")
    var centroStockId: Int? = null,

    @Column(name = "inv_art_id")
    var articuloId: String? = null,

    @Column(name = "inv_cantidad")
    var cantidad: BigDecimal = BigDecimal("0.000"),

    @Column(name = "inv_stock")
    var stock: BigDecimal = BigDecimal("0.000"),

    @OneToOne
    @JoinColumn(name = "inv_int_id", updatable = false, insertable = false)
    var inventarioTurno: InventarioTurno? = null,

    @OneToOne
    @JoinColumn(name = "inv_cst_id", updatable = false, insertable = false)
    var centroStock: CentroStock? = null,

    @OneToOne
    @JoinColumn(name = "inv_art_id", updatable = false, insertable = false)
    var articulo: Articulo? = null

) : Auditable() {

    class Builder {
        private var inventarioId: Long? = null
        private var fecha: OffsetDateTime? = null
        private var inventarioTurnoId: Int? = null
        private var centroStockId: Int? = null
        private var articuloId: String? = null
        private var cantidad: BigDecimal = BigDecimal("0.000")
        private var stock: BigDecimal = BigDecimal("0.000")
        private var inventarioTurno: InventarioTurno? = null
        private var centroStock: CentroStock? = null
        private var articulo: Articulo? = null

        fun inventarioId(inventarioId: Long?) = apply { this.inventarioId = inventarioId }
        fun fecha(fecha: OffsetDateTime?) = apply { this.fecha = fecha }
        fun inventarioTurnoId(inventarioTurnoId: Int?) = apply { this.inventarioTurnoId = inventarioTurnoId }
        fun centroStockId(centroStockId: Int?) = apply { this.centroStockId = centroStockId }
        fun articuloId(articuloId: String?) = apply { this.articuloId = articuloId }
        fun cantidad(cantidad: BigDecimal) = apply { this.cantidad = cantidad }
        fun stock(stock: BigDecimal) = apply { this.stock = stock }
        fun inventarioTurno(inventarioTurno: InventarioTurno?) = apply { this.inventarioTurno = inventarioTurno }
        fun centroStock(centroStock: CentroStock?) = apply { this.centroStock = centroStock }
        fun articulo(articulo: Articulo?) = apply { this.articulo = articulo }

        fun build() = Inventario(
            inventarioId, fecha, inventarioTurnoId, centroStockId, articuloId, cantidad, stock, inventarioTurno, centroStock, articulo
        )
    }
}