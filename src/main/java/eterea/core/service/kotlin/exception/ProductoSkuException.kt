package eterea.core.service.kotlin.exception

class ProductoSkuException(sku: String) : RuntimeException("Cannot find ProductoSku $sku")