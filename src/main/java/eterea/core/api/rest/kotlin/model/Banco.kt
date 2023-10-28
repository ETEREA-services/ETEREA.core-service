package eterea.core.api.rest.kotlin.model

import eterea.core.api.rest.model.Auditable
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table
data class Banco(

    @Id
    @Column(name = "ban_id")
    var bancoId: Int? = null,

    @Column(name = "ban_nombre")
    var nombre: String? = null


) : Auditable()