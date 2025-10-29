package eterea.core.service.kotlin.model

import eterea.core.service.model.Auditable
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "habitaciontipo")
data class HabitacionTipo(

    @Id
    @Column(name = "hat_id")
    var habitacionTipoId: Int? = null,

    @Column(name = "hat_nombre")
    var nombre: String = "",

    @Column(name = "hat_paxs")
    var paxs: Int? = null

) : Auditable()
