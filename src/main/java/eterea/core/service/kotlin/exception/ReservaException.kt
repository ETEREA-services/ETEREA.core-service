package eterea.core.service.kotlin.exception

class ReservaException(reservaId: Long) : RuntimeException("Cannot find Reserva $reservaId")