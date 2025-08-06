package eterea.core.service.kotlin.repository

import eterea.core.service.kotlin.model.ArticuloMovimiento
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface ArticuloMovimientoRepository : JpaRepository<ArticuloMovimiento, Long> {
    fun findAllByClienteMovimientoId(clienteMovimientoId: Long): List<ArticuloMovimiento?>?

    fun findByArticuloMovimientoId(articuloMovimientoId: Long): Optional<ArticuloMovimiento?>?

    fun findAllByClienteMovimientoIdIn(clienteMovimientoIds: List<Long>): List<ArticuloMovimiento?>?

}
