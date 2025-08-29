package eterea.core.service.kotlin.exception

import java.time.OffsetDateTime

class HabitacionMovimientoNotDeletableException(numeroReserva: Long, reservaEstado: String) : RuntimeException("Reserva $numeroReserva no es editable debido a que se encuentra en estado $reservaEstado")