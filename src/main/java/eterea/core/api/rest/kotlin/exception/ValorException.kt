package eterea.core.api.rest.kotlin.exception

class ValorException(valorId: Int) : RuntimeException("Cannot find Valor $valorId")