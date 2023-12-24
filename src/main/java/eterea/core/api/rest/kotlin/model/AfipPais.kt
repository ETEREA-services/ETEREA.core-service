package eterea.core.api.rest.kotlin.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "paisafip")
data class AfipPais(

    @Id
    @Column(name = "pais_id")
    var afipPaisId: Int? = null,

    var nombre: String = "",

    @Column(name = "auto_id")
    var autoId: Long? = null

) : Auditable()
