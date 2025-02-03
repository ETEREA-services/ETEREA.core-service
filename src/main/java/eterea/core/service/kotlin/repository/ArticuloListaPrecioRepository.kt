package eterea.core.service.kotlin.repository

import eterea.core.service.kotlin.model.ArticuloListaPrecio
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import java.math.BigDecimal
import java.util.Optional

interface ArticuloListaPrecioRepository : JpaRepository<ArticuloListaPrecio, String> {

    fun findAllByPublicarAndArticuloPrecioVentaConIvaGreaterThan(
        publicar: Byte, 
        precioVentaConIva: BigDecimal, 
        pageable: Pageable
    ): Page<ArticuloListaPrecio>

    fun findByArticuloId(articuloId: String): Optional<ArticuloListaPrecio?>?
}