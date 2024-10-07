package eterea.core.service.kotlin.exception

import java.time.OffsetDateTime

class FeriadoException(fecha: OffsetDateTime) : RuntimeException("Cannot find Feriado $fecha")