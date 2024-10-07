package eterea.core.service.kotlin.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "reservaorigen")
data class ReservaOrigen(

    @Id
    @Column(name = "reo_id")
    var reservaOrigenId: Int? = null,

    @Column(name = "reo_nombre")
    var nombre: String? = null

) : Auditable()
