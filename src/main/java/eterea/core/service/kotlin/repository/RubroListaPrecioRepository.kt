package eterea.core.service.kotlin.repository

import eterea.core.service.kotlin.model.RubroListaPrecio
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface RubroListaPrecioRepository : JpaRepository<RubroListaPrecio?, Long?> {

    fun findAllByOrderByRubroDescripcion(): List<RubroListaPrecio>

    fun findAllByPublicarOrderByRubroDescripcion(publicar: Byte): List<RubroListaPrecio>

    fun findByRubroId(rubroId: Long?): Optional<RubroListaPrecio>

}