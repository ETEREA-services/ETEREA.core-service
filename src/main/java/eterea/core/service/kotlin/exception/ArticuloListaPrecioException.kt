package eterea.core.service.kotlin.exception

class ArticuloListaPrecioException(articuloId: String) : RuntimeException("ArticuloListaPrecio not found for articuloId: $articuloId")