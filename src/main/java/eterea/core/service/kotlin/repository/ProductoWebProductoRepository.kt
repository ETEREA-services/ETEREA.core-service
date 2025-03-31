package eterea.core.service.kotlin.repository

import eterea.core.service.kotlin.model.ProductoWebProducto
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

interface ProductoWebProductoRepository : JpaRepository<ProductoWebProducto, Long> {
}