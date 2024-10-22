package eterea.core.service.kotlin.exception.view

import java.time.OffsetDateTime

class SaldoFechaException(centroStockId: Int, articuloId: String, fecha: OffsetDateTime) : RuntimeException("Cannot find SaldoFecha for CentroStock -> $centroStockId, Articulo -> $articuloId, Fecha -> $fecha")