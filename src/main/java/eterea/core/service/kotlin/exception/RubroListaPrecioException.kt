package eterea.core.service.kotlin.exception

class RubroListaPrecioException(rubroId: Long) : RuntimeException("Cannot find Rubro $rubroId")