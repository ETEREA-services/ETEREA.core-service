package eterea.core.service.kotlin.repository

import eterea.core.service.kotlin.model.StockMovimiento
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface StockMovimientoRepository : JpaRepository<StockMovimiento, Long> {

    fun findFirstByComprobanteIdOrderByStockMovimientoIdDesc(comprobanteId: Int): Optional<StockMovimiento?>?

}