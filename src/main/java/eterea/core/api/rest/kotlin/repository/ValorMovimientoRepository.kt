package eterea.core.api.rest.kotlin.repository

import eterea.core.api.rest.kotlin.model.ValorMovimiento
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface ValorMovimientoRepository : JpaRepository<ValorMovimiento, Int> {

    fun findByValorMovimientoId(valorMovimientoId: Long): Optional<ValorMovimiento?>?

}