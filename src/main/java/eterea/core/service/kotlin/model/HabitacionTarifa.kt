package eterea.core.service.kotlin.model

import eterea.core.service.model.Auditable
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.persistence.UniqueConstraint

@Entity
@Table(
    name = "habitaciontarifa",
    uniqueConstraints = [UniqueConstraint(columnNames = ["hta_numero", "hta_paxs"])]
)
data class HabitacionTarifa(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hta_id")
    var habitacionTarifaId: Long? = null,

    @Column(name = "hta_numero")
    var numero: Int = 0,

    @Column(name = "hta_paxs")
    var paxs: Int = 0,

    @Column(name = "hta_hat_id")
    var habitacionTipoId: Int = 0

) : Auditable()
