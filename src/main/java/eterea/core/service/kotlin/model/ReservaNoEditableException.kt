package eterea.core.service.kotlin.exception

class ReservaNoEditableException(reservaId: Long, reservaEstado: String) : RuntimeException("Reserva $reservaId no es editable debido a que se encuentra en estado $reservaEstado")
