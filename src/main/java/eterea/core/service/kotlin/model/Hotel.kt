package eterea.core.service.kotlin.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity
data class Hotel(

    @Id
    @Column(name = "hot_id")
    var hotelId: Int? = null,

    @Column(name = "hot_nombre")
    var nombre: String = "",

    var extras: Byte = 0,
    var paradaTraslado: Byte = 0,
    var puntoEncuentro: Byte = 0,

) : Auditable()
