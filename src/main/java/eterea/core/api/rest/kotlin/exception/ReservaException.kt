package eterea.core.api.rest.kotlin.exception

class ReservaException(reservaId: Long) : RuntimeException("Cannot find Reserva $reservaId")