package eterea.core.api.rest.kotlin.extern

data class ProductTransaction(

    var productTransactionId: Long? = null,
    var orderNumberId: Long? = null,
    var nombreProducto: String = "",
    var montoProducto: String? = null

)
