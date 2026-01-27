package eterea.core.service.kotlin.repository

import eterea.core.service.kotlin.model.CentroStock
import org.springframework.data.jpa.repository.JpaRepository

interface CentroStockRepository : JpaRepository<CentroStock, Int> {
}