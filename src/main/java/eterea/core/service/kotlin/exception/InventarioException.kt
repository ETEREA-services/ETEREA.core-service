package eterea.core.service.kotlin.exception

import java.time.OffsetDateTime

class InventarioException(fecha: OffsetDateTime, inventarioTurnoId: Int, centroStockId: Int, articuloId: String) : RuntimeException("Inventario not found fecha: $fecha, inventarioTurnoId: $inventarioTurnoId, centroStockId: $centroStockId, articuloId: $articuloId")