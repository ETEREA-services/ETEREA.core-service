package eterea.core.api.rest.kotlin.repository

import eterea.core.api.rest.kotlin.model.CentroStock
import org.springframework.data.jpa.repository.JpaRepository

interface CentroStockRepository : JpaRepository<CentroStock, Int?> {
}