package eterea.core.service.kotlin.model.dto

import java.math.BigDecimal
import java.time.OffsetDateTime

data class PriceDto(

    var articuloId: String? = null,
    var fechaInicio: OffsetDateTime? = null,
    var fechaFin: OffsetDateTime? = null,
    var precioLunes: BigDecimal = BigDecimal.ZERO,
    var precioMartes: BigDecimal = BigDecimal.ZERO,
    var precioMiercoles: BigDecimal = BigDecimal.ZERO,
    var precioJueves: BigDecimal = BigDecimal.ZERO,
    var precioViernes: BigDecimal = BigDecimal.ZERO,
    var precioSabado: BigDecimal = BigDecimal.ZERO,
    var precioDomingo: BigDecimal = BigDecimal.ZERO,
    var precioFeriado: BigDecimal = BigDecimal.ZERO

)
