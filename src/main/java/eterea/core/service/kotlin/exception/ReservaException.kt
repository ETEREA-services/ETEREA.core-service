package eterea.core.service.kotlin.exception

import java.time.OffsetDateTime

class ReservaException : RuntimeException {
    constructor(reservaId: Long) : super("Cannot find Reserva $reservaId")
    constructor(habitacionId: Long, fechaIngreso: OffsetDateTime, fechaSalida: OffsetDateTime) : 
        super("Habitacion con id: $habitacionId no disponible para las fechas: $fechaIngreso hasta $fechaSalida")
    constructor(message: String) : super(message)
}