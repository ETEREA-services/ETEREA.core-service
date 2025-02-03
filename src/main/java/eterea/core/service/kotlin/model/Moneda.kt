package eterea.core.service.kotlin.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity
data class Moneda(

    @Id
    @Column(name = "mon_id")
    var monedaId: Int? = null,

    @Column(name = "mon_nombre")
    var nombre: String? = "",

    @Column(name = "mon_simbolo")
    var simbolo: String? = "",

    @Column(name = "mon_cva_id")
    var valorId: Int? = null

) : Auditable()
