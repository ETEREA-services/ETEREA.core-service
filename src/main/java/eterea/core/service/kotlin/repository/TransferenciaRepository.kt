package eterea.core.service.kotlin.repository

import eterea.core.service.kotlin.model.Transferencia
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface TransferenciaRepository : JpaRepository<Transferencia, Long> {

    fun findByNegocioIdDesdeAndNegocioIdHastaAndNumeroTransferencia(
        negocioIdDesde: Int,
        negocioIdHasta: Int,
        numeroTransferencia: Long
    ): Optional<Transferencia?>?

}