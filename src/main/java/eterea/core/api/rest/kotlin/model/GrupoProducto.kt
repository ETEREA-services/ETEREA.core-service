package eterea.core.api.rest.kotlin.model

import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(
    name = "grupoproducto",
    uniqueConstraints = [UniqueConstraint(columnNames = ["grp_gru_id", "grp_prd_id"])]
)
data class GrupoProducto(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "grp_id")
    var grupoProductoId: Long? = null,

    @Column(name = "grp_gru_id")
    var grupoId: Int? = null,

    @Column(name = "grp_prd_id")
    var productoId: Int? = null,

    @Column(name = "grp_coeficiente")
    var coeficiente: BigDecimal = BigDecimal.ZERO

) : Auditable()
