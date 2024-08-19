package eterea.core.api.rest.kotlin.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.math.BigDecimal

@Entity
data class Legajo(

    @Id
    @Column(name = "leg_id")
    var legajoId: Int? = null,

    @Column(name = "leg_apellido")
    var apellido: String = "",

    @Column(name = "leg_nombre")
    var nombre: String = "",

    @Column(name = "leg_nrodoc")
    var numeroDocumento: BigDecimal? = null,

    @Column(name = "leg_cuil")
    var cuil: String = ""

) : Auditable()
