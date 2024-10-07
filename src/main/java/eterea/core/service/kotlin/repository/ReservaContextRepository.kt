package eterea.core.service.kotlin.repository

import eterea.core.service.kotlin.model.ReservaContext
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface ReservaContextRepository : JpaRepository<ReservaContext, Long> {

    fun findByReservaContextId(reservaContextId: Long): Optional<ReservaContext?>?

    fun findAllByFacturaPendiente(facturaPendiente: Byte): List<ReservaContext>

    fun findByVoucherId(voucherId: Long): Optional<ReservaContext?>?

    fun findByReservaId(reservaId: Long): Optional<ReservaContext>

}