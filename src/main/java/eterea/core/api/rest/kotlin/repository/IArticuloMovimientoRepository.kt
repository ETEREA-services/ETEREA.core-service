package eterea.core.api.rest.kotlin.repository

import eterea.core.api.rest.kotlin.model.ArticuloMovimiento
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface IArticuloMovimientoRepository : JpaRepository<ArticuloMovimiento?, Long?> {
    fun findAllByClienteMovimientoId(clienteMovimientoId: Long?): List<ArticuloMovimiento?>?

    fun findByArticuloMovimientoId(articuloMovimientoId: Long): Optional<ArticuloMovimiento?>?

}
