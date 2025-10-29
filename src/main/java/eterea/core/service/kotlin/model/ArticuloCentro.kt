package eterea.core.service.kotlin.model

import eterea.core.service.model.Auditable
import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(
    name = "articuloscentro",
    uniqueConstraints = [UniqueConstraint(columnNames = ["arc_art_id", "arc_cst_id"])]
)
data class ArticuloCentro(

    @Id
    @Column(name = "arc_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var articuloCentroId: Long? = null,

    @Column(name = "arc_art_id")
    var articuloId: String? = null,

    @Column(name = "arc_cst_id")
    var centroStockId: Int? = null,

    @Column(name = "arc_saldo")
    var saldo: BigDecimal = BigDecimal.ZERO,

    @Column(name = "arc_saldosf")
    var saldoStockFicha: BigDecimal = BigDecimal.ZERO

) : Auditable() {

    class Builder {
        private var articuloCentroId: Long? = null
        private var articuloId: String? = null
        private var centroStockId: Int? = null
        private var saldo: BigDecimal = BigDecimal.ZERO
        private var saldoStockFicha: BigDecimal = BigDecimal.ZERO

        fun articuloCentroId(articuloCentroId: Long?) = apply { this.articuloCentroId = articuloCentroId }
        fun articuloId(articuloId: String?) = apply { this.articuloId = articuloId }
        fun centroStockId(centroStockId: Int?) = apply { this.centroStockId = centroStockId }
        fun saldo(saldo: BigDecimal) = apply { this.saldo = saldo }
        fun saldoStockFicha(saldoStockFicha: BigDecimal) = apply { this.saldoStockFicha = saldoStockFicha }

        fun build() = ArticuloCentro(
            articuloCentroId = articuloCentroId,
            articuloId = articuloId,
            centroStockId = centroStockId,
            saldo = saldo,
            saldoStockFicha = saldoStockFicha
        )
    }
}