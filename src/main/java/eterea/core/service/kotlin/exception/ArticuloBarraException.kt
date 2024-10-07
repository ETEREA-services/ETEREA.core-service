package eterea.core.service.kotlin.exception

class ArticuloBarraException(codigobarras: String) : RuntimeException("Cannot found ArticuloBarra with code $codigobarras")
