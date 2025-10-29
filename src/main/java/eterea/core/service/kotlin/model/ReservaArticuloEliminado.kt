package eterea.core.service.kotlin.model

import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "reservaarticuloe")
data class ReservaArticuloEliminado(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rae_id")
    var reservaArticuloEliminadoId: Long? = null,

    @Column(name = "rae_neg_id")
    var negocioId: Int? = null,

    @Column(name = "rae_res_id")
    var reservaId: Long? = null,

    @Column(name = "rae_vou_id")
    var voucherId: Long? = null,

    @Column(name = "rae_art_id")
    var articuloId: String? = null,

    @Column(name = "rae_cantidad")
    var cantidad: Int = 0,

    @Column(name = "rae_comision")
    var comision: BigDecimal = BigDecimal.ZERO,

    @Column(name = "rae_preciounitsincomision")
    var precioUnitarioSinComision: BigDecimal = BigDecimal.ZERO,

    @Column(name = "rae_preciounitario")
    var precioUnitario: BigDecimal = BigDecimal.ZERO,

    @Column(name = "rae_preciocompra")
    var precioCompra: BigDecimal = BigDecimal.ZERO,

    @Column(name = "rae_observaciones")
    var observaciones: String = "",

    @Column(name = "rae_rar_id")
    var reservaArticuloId: Long? = null,
) : Auditable() {
    data class Builder(
        var reservaArticuloEliminadoId: Long? = null,
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
        var reservaArticuloId: Long? = null,
    ) {
        fun reservaArticuloEliminadoId(reservaArticuloEliminadoId: Long?) = apply { this.reservaArticuloEliminadoId = reservaArticuloEliminadoId }
        fun negocioId(negocioId: Int?) = apply { this.negocioId = negocioId }
        fun reservaId(reservaId: Long?) = apply { this.reservaId = reservaId }
        fun voucherId(voucherId: Long?) = apply { this.voucherId = voucherId }
        fun articuloId(articuloId: String?) = apply { this.articuloId = articuloId }
        fun cantidad(cantidad: Int) = apply { this.cantidad = cantidad }
        fun comision(comision: BigDecimal) = apply { this.comision = comision }
        fun precioUnitarioSinComision(precioUnitarioSinComision: BigDecimal) = apply { this.precioUnitarioSinComision = precioUnitarioSinComision }
        fun precioUnitario(precioUnitario: BigDecimal) = apply { this.precioUnitario = precioUnitario }
        fun precioCompra(precioCompra: BigDecimal) = apply { this.precioCompra = precioCompra }
        fun observaciones(observaciones: String) = apply { this.observaciones = observaciones }
        fun reservaArticuloId(reservaArticuloId: Long?) = apply { this.reservaArticuloId = reservaArticuloId }
        fun build() = ReservaArticuloEliminado(
            reservaArticuloEliminadoId, negocioId, reservaId, voucherId, articuloId, cantidad, comision,
            precioUnitarioSinComision, precioUnitario, precioCompra, observaciones, reservaArticuloId
        )
    }
}