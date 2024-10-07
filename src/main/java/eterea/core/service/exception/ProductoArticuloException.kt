package eterea.core.service.exception

class ProductoArticuloException(productoId: Int, articuloId: String) : RuntimeException("Cannot find ProductoArticulo $productoId/$articuloId")