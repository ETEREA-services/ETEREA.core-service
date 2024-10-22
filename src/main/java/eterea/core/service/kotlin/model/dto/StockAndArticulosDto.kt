package eterea.core.service.kotlin.model.dto

import eterea.core.service.kotlin.model.ArticuloMovimiento
import eterea.core.service.kotlin.model.StockMovimiento

data class StockAndArticulosDto(

    var stockMovimiento: StockMovimiento? = null,
    var articuloMovimientos: List<ArticuloMovimiento>? = null

)
