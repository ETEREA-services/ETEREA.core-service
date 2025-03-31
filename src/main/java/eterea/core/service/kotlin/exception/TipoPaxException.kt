package eterea.core.service.kotlin.exception

class TipoPaxException(nombre: String) : RuntimeException("TipoPax con nombre: $nombre no encontrado")