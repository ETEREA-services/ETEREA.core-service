package eterea.core.service.kotlin.repository

import eterea.core.service.kotlin.model.ArticuloListaPrecio
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import java.math.BigDecimal
import java.util.Optional
import java.util.UUID

interface ArticuloListaPrecioRepository : JpaRepository<ArticuloListaPrecio, UUID> {

    fun findAllByPublicarAndArticuloPrecioVentaConIvaGreaterThan(
        publicar: Byte, 
        precioVentaConIva: BigDecimal, 
        pageable: Pageable
    ): Page<ArticuloListaPrecio>

    fun findByArticuloId(articuloId: String): Optional<ArticuloListaPrecio?>?

    fun findAllByPublicarAndArticuloPrecioVentaConIvaGreaterThanAndArticuloRubroId(
        publicar: Byte,
        precioVentaConIva: BigDecimal,
        rubroId: Long,
        pageable: Pageable
    ): Page<ArticuloListaPrecio>
    
}