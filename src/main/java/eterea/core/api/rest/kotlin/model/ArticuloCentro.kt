package eterea.core.api.rest.kotlin.model

import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "articuloscentro", uniqueConstraints = [UniqueConstraint(columnNames = ["arc_art_id", "arc_cst_id"])])
data class ArticuloCentro(

    @Column(name = "arc_art_id")
    var articuloId: String? = null,

    @Column(name = "arc_cst_id")
    var centroStockId: Int? = null,

    @Column(name = "arc_saldo")
    var saldo: BigDecimal = BigDecimal.ZERO,

    @Column(name = "saldosf")
    var saldoStockFicha: BigDecimal = BigDecimal.ZERO,

    @Id
    @Column(name = "arc_id")
    var articuloCentroId: Long? = null

) : Auditable()
