package eterea.core.api.rest.exception

class ProductoArticuloException(productoId: Int, articuloId: String) : RuntimeException("Cannot find ProductoArticulo $productoId/$articuloId")