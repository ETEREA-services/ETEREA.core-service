package eterea.core.service.kotlin.model

import eterea.core.service.model.Auditable
import jakarta.persistence.*

@Entity
data class Grupo(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gru_id")
    var grupoId: Int? = null,

    @Column(name = "gru_nombre")
    var nombre: String = "",

    var ventaInternet: Byte = 0

) : Auditable()
