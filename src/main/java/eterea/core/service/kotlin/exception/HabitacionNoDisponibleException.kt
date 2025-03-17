package eterea.core.service.kotlin.exception

import java.time.OffsetDateTime

class HabitacionNoDisponibleException(numeroHabitacion: Int, fechaIngreso: OffsetDateTime, fechaSalida: OffsetDateTime) : RuntimeException("Habitacion numero $numeroHabitacion no disponible para las fechas: $fechaIngreso hasta $fechaSalida")
