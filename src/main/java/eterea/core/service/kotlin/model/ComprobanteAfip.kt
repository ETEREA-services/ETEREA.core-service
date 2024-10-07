package eterea.core.service.kotlin.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "compafip")
data class ComprobanteAfip(

    @Id
    @Column(name = "caf_id")
    var comprobanteAfipId: Int? = null,

    @Column(name = "caf_nombre")
    var nombre: String? = null,

    @Column(name = "caf_label")
    var label: String? = null

)
