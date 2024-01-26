package eterea.core.api.rest.kotlin.exception

class ValorMovimientoException(valorMovimientoId: Long) : RuntimeException("Cannot find ValorMovimiento $valorMovimientoId")