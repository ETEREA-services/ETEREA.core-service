package eterea.core.api.rest.kotlin.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "articulosbarras")
data class ArticuloBarra(

    @Id @Column(name = "aba_codigo")
    var codigo: String? = null,

    @Column(name = "clave")
    var articuloBarraId: Long? = null,

    @Column(name = "aba_art_id")
    var articuloId: String? = null

) : Auditable()
