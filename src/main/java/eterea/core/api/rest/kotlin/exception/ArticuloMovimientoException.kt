package eterea.core.api.rest.kotlin.exception

class ArticuloMovimientoException(articuloMovimientoId: Long) : RuntimeException("Cannot find ArticuloMovimiento $articuloMovimientoId")