package eterea.core.service.kotlin.exception

class ValorMovimientoException(valorMovimientoId: Long) : RuntimeException("Cannot find ValorMovimiento $valorMovimientoId")