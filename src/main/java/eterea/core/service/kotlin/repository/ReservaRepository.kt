package eterea.core.service.kotlin.repository

import eterea.core.service.kotlin.model.Reserva
import java.time.OffsetDateTime
import java.util.*
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Slice
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

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

    fun findTopByOrderByReservaIdDesc(): Optional<Reserva?>?

    fun findAllByFechaTomaBetweenAndVerificada(
            desde: OffsetDateTime?,
            hasta: OffsetDateTime?,
            verificada: Byte
    ): List<Reserva?>?

    fun findSliceByVerificadaAndFacturadaAndEliminadaAndPagaCacheutaAndFacturadoFueraAndAnuladaAndClienteIdGreaterThan(
            verificada: Byte?,
            facturada: Byte?,
            eliminada: Byte?,
            pagaCacheuta: Byte?,
            facturadoFuera: Byte?,
            anulada: Byte?,
            clienteId: Long?,
            pageable: Pageable?
    ): Slice<Reserva>?
}
