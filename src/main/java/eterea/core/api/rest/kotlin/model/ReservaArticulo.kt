package eterea.core.api.rest.kotlin.model

import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "reservaarticulo")
data class ReservaArticulo(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rar_id")
    var reservaArticuloId: Long? = null,

    @Column(name = "rar_neg_id")
    var negocioId: Int? = null,

    @Column(name = "rar_res_id")
    var reservaId: Long? = null,

    @Column(name = "rar_vou_id")
    var voucherId: Long? = null,

    @Column(name = "rar_art_id")
    var articuloId: String? = null,

    @Column(name = "rar_cantidad")
    var cantidad: Int = 0,

    @Column(name = "rar_comision")
    var comision: BigDecimal = BigDecimal.ZERO,

    @Column(name = "rar_preciounitsincomision")
    var precioUnitarioSinComision: BigDecimal = BigDecimal.ZERO,

    @Column(name = "rar_preciounitario")
    var precioUnitario: BigDecimal = BigDecimal.ZERO,

    @Column(name = "rar_preciocompra")
    var precioCompra: BigDecimal = BigDecimal.ZERO,

    @Column(name = "rar_observaciones")
    var observaciones: String = "",

    @OneToOne(optional = true)
    @JoinColumn(name = "rar_art_id", insertable = false, updatable = false)
    var articulo: Articulo? = null

) : Auditable() {
    data class Builder(
        var reservaArticuloId: Long? = null,
        var negocioId: Int? = null,
        var reservaId: Long? = null,
        var voucherId: Long? = null,
        var articuloId: String? = null,
        var cantidad: Int = 0,
        var comision: BigDecimal = BigDecimal.ZERO,
        var precioUnitarioSinComision: BigDecimal = BigDecimal.ZERO,
        var precioUnitario: BigDecimal = BigDecimal.ZERO,
        var precioCompra: BigDecimal = BigDecimal.ZERO,
        var observaciones: String = "",
        var articulo: Articulo? = null,
    ) {
        fun reservaArticuloId(reservaArticuloId: Long?) = apply { this.reservaArticuloId = reservaArticuloId }
        fun negocioId(negocioId: Int?) = apply { this.negocioId = negocioId }
        fun reservaId(reservaId: Long?) = apply { this.reservaId = reservaId }
        fun voucherId(voucherId: Long?) = apply { this.voucherId = voucherId }
        fun articuloId(articuloId: String?) = apply { this.articuloId = articuloId }
        fun cantidad(cantidad: Int) = apply { this.cantidad = cantidad }
        fun comision(comision: BigDecimal) = apply { this.comision = comision }
        fun precioUnitarioSinComision(precioUnitarioSinComision: BigDecimal) =
            apply { this.precioUnitarioSinComision = precioUnitarioSinComision }

        fun precioUnitario(precioUnitario: BigDecimal) = apply { this.precioUnitario = precioUnitario }
        fun precioCompra(precioCompra: BigDecimal) = apply { this.precioCompra = precioCompra }
        fun observaciones(observaciones: String) = apply { this.observaciones = observaciones }
        fun articulo(articulo: Articulo?) = apply {this.articulo = articulo}

        fun build() = ReservaArticulo(
            reservaArticuloId, negocioId, reservaId, voucherId, articuloId, cantidad, comision,
            precioUnitarioSinComision, precioUnitario, precioCompra, observaciones, articulo
        )
    }
}
