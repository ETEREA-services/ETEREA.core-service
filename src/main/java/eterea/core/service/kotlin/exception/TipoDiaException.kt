package eterea.core.service.kotlin.exception

class TipoDiaException(nombre: String) :
        RuntimeException("TipoDia con nombre: $nombre no encontrado")
