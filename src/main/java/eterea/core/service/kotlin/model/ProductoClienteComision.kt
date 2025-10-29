package eterea.core.service.kotlin.model

import eterea.core.service.model.Auditable
import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(
    name = "productoclientecomision",
    uniqueConstraints = [UniqueConstraint(columnNames = ["pcc_prd_id", "pcc_cli_id"])]
)
data class ProductoClienteComision(

    @Column(name = "pcc_prd_id")
    var productoId: Int? = null,

    @jakarta.persistence.Column(name = "pcc_cli_id")
    var clienteId: Long? = null,

    @Column(name = "pcc_neg_id")
    var negocioId: Int? = null,

    @Column(name = "pcc_comision")
    var comision: BigDecimal = BigDecimal.ZERO,

    @Id
    @Column(name = "pcc_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var productoClienteComisionId: Long? = null

) : Auditable()
