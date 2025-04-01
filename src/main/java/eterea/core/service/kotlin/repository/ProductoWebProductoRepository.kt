package eterea.core.service.kotlin.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import eterea.core.service.kotlin.model.ProductoWebProducto
import eterea.core.service.kotlin.model.TipoDia
import eterea.core.service.kotlin.model.TipoPax
import eterea.core.service.kotlin.model.ProductoWeb

@Repository
interface ProductoWebProductoRepository : JpaRepository<ProductoWebProducto, Long> {
   fun findByProductoWebAndTipoPaxAndTipoDia(productoWeb: ProductoWeb, tipoPax: TipoPax, tipoDia: TipoDia): List<ProductoWebProducto>;
}