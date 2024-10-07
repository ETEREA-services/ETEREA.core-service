package eterea.core.service.kotlin.extern

import java.math.BigDecimal
import java.time.OffsetDateTime

data class Product(

    var productId: Long? = null,
    var orderNumberId: Long? = null,
    var sku: String? = "",
    var lineId: Int = 0,
    var name: String = "",
    var qty: Int = 0,
    var itemPrice: BigDecimal = BigDecimal.ZERO,
    var bookingStart: OffsetDateTime? = null,
    var bookingEnd: OffsetDateTime? = null,
    var bookingDuration: Int = 0,
    var bookingPersons: Int = 0,
    var personTypes: String = "",
    var serviciosAdicionales: String = "",
    var puntoDeEncuentro: String = "",
    var encuentroHotel: String = ""

)
