package eterea.core.api.rest.kotlin.model.view

import com.fasterxml.jackson.annotation.JsonFormat
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.math.BigDecimal
import java.time.OffsetDateTime

@Entity
@Table(name = "vw_cuenta_movimiento_apertura_dia")
data class CuentaMovimientoAperturaDia(

    @Id
    var uniqueId: String? = null,

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
    var fecha: OffsetDateTime? = null,
    var debita: Int = 0,
    var numeroCuenta: Long? = null,
    var negocioId: Int? = null,
    var importe: BigDecimal = BigDecimal.ZERO,
    var cantidad: Int = 0

)
