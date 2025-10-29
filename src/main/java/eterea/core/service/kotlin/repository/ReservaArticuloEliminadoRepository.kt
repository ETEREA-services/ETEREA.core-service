package eterea.core.service.kotlin.repository

import eterea.core.service.kotlin.model.ReservaArticuloEliminado
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ReservaArticuloEliminadoRepository : JpaRepository<ReservaArticuloEliminado, Long> {

   fun findAllByReservaId(reservaId: Long): List<ReservaArticuloEliminado?>?
}
