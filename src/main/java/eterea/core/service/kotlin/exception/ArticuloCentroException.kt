package eterea.core.service.kotlin.exception

class ArticuloCentroException(centroStockId: Int, articuloId: String) : RuntimeException("Cannot find ArticuloCentro for CentroStock -> $centroStockId, Articulo -> $articuloId")