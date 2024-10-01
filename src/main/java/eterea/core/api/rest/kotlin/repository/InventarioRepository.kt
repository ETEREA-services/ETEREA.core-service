package eterea.core.api.rest.kotlin.repository

import eterea.core.api.rest.kotlin.model.Inventario
import org.springframework.data.jpa.repository.JpaRepository
import java.time.OffsetDateTime
import java.util.Optional

interface InventarioRepository : JpaRepository<Inventario, Long> {

    fun findByFechaAndInventarioTurnoIdAndCentroStockIdAndArticuloId(fecha: OffsetDateTime, inventarioTurnoId: Int, centroStockId: Int, articuloId: String): Optional<Inventario?>?

}