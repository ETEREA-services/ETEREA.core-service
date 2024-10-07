package eterea.core.service.kotlin.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.math.BigDecimal

@Entity
@Table(name = "tarifashabitaciones")
data class TarifaHabitacion(

    @Id
    @Column(name = "codigo")
    var tarifaHabitacionId: Long? = null,

    var descripcion: String = "",
    var precio: BigDecimal = BigDecimal.ZERO,

    @Column(name = "bloqueofactura")
    var bloqueoFactura: Byte = 0,

    @Column(name = "minimoanticipo")
    var minimoAnticipo: BigDecimal = BigDecimal.ZERO

) : Auditable()
