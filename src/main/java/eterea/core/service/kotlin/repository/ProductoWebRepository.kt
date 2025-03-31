package eterea.core.service.kotlin.repository

import eterea.core.service.kotlin.model.ProductoWeb
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface ProductoWebRepository : JpaRepository<ProductoWeb, Long> {
    fun findBySku(sku: String): Optional<ProductoWeb>
}