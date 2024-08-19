package eterea.core.api.rest.kotlin.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity
data class Habitacion(

    @Id
    @Column(name = "hab_numero")
    var numero: Int? = null,

    @Column(name = "hab_hat_id")
    var habitacionTipoId: Int? = null,

    @Column(name = "hab_cli_id")
    var clienteId: Long? = null

) : Auditable()
