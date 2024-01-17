package eterea.core.api.rest.kotlin.repository

import eterea.core.api.rest.kotlin.model.ReservaArticulo
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface ReservaArticuloRepository : JpaRepository<ReservaArticulo, Long> {

    fun findAllByReservaId(reservaId: Long): List<ReservaArticulo?>?

    fun findAllByReservaIdAndVoucherId(reservaId: Long, voucherId: Long): List<ReservaArticulo?>?

    fun findByReservaArticuloId(reservaArticuloId: Long): Optional<ReservaArticulo?>?

    fun deleteByReservaArticuloId(reservaArticuloId: Long)

}