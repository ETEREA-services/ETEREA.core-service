package eterea.core.service.kotlin.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import jakarta.persistence.UniqueConstraint
import java.math.BigDecimal
import java.time.OffsetDateTime

@Entity
@Table(name = "articulossaldoffecha", uniqueConstraints = [UniqueConstraint(columnNames = ["asf_cst_id", "asf_art_id", "asf_fecha"])])
data class ArticuloSaldoFecha(

    @Id
    @Column(name = "clave")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var articuloSaldoFechaId: Long? = null,

    @Column(name = "asf_cst_id")
    var centroStockId: Int? = null,

    @Column(name = "asf_art_id")
    var articuloId: String? = null,

    @Column(name = "asf_fecha")
    var fecha: OffsetDateTime? = null,

    @Column(name = "asf_saldo")
    var saldo: BigDecimal = BigDecimal.ZERO,

    @OneToOne(optional = true)
    @JoinColumn(name = "asf_cst_id", insertable = false, updatable = false)
    var centroStock: CentroStock? = null,

    @OneToOne(optional = true)
    @JoinColumn(name = "asf_art_id", insertable = false, updatable = false)
    var articulo: Articulo? = null

) : Auditable()
