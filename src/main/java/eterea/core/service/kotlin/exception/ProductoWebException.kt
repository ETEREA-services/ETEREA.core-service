package eterea.core.service.kotlin.exception

class ProductoWebException(sku: String) :
        RuntimeException("Producto Web con sku: $sku no encontrado")
