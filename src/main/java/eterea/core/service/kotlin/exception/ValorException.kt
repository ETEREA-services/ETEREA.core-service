package eterea.core.service.kotlin.exception

class ValorException(valorId: Int) : RuntimeException("Cannot find Valor $valorId")