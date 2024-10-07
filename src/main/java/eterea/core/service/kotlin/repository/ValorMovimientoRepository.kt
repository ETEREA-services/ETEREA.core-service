package eterea.core.service.kotlin.repository

import eterea.core.service.kotlin.model.ValorMovimiento
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface ValorMovimientoRepository : JpaRepository<ValorMovimiento, Int> {

    fun findByValorMovimientoId(valorMovimientoId: Long): Optional<ValorMovimiento?>?

}