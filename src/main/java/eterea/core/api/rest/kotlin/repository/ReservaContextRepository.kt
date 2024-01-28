package eterea.core.api.rest.kotlin.repository

import eterea.core.api.rest.kotlin.model.ReservaContext
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface ReservaContextRepository : JpaRepository<ReservaContext, Long> {

    fun findByReservaContextId(reservaContextId: Long): Optional<ReservaContext?>?

    fun findByVoucherId(voucherId: Long): Optional<ReservaContext?>?

}