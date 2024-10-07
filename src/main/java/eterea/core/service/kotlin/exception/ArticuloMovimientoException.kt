package eterea.core.service.kotlin.exception

class ArticuloMovimientoException(articuloMovimientoId: Long) : RuntimeException("Cannot find ArticuloMovimiento $articuloMovimientoId")