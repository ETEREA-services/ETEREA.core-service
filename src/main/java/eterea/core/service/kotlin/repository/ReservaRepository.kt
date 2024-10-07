package eterea.core.service.kotlin.repository

import eterea.core.service.kotlin.model.Reserva
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ReservaRepository : JpaRepository<Reserva, Long> {

    fun findByReservaId(reservaId: Long?): Optional<Reserva?>?

    fun findTop100ByVerificadaAndFacturadaAndEliminadaAndPagaCacheutaAndFacturadoFueraAndAnuladaAndClienteIdGreaterThan(
        verificada: Byte?,
        facturada: Byte?,
        eliminada: Byte?,
        pagaCacheuta: Byte?,
        facturadoFuera: Byte?,
        anulada: Byte?,
        clienteId: Long?,
        sort: Sort?
    ): List<Reserva?>?

}