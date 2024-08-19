package eterea.core.api.rest.kotlin.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.validation.constraints.Size

@Entity
@Table(name = "clienteinternet")
data class ClienteInternet(

    @Id
    @Column(name = "cliente_id")
    var clienteId: Long? = null,

    @Column(name = "password")
    @Size(max = 64)
    var password: String? = null

) : Auditable()
