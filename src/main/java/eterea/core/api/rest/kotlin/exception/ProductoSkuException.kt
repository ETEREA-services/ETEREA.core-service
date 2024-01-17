package eterea.core.api.rest.kotlin.exception

class ProductoSkuException(sku: String) : RuntimeException("Cannot find ProductoSku $sku")