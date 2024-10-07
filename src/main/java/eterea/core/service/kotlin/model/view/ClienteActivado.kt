package eterea.core.service.kotlin.model.view

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.springframework.data.annotation.Immutable

@Entity
@Immutable
@Table(name = "vw_clienteactivado")
data class ClienteActivado(

    @Id
    var clienteId: Long? = null,
    var nombreFantasia: String? = null,
    var razonSocial: String? = null,
    var nombre: String? = null

)
