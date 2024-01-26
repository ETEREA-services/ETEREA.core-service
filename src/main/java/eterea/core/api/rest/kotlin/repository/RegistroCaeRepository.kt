package eterea.core.api.rest.kotlin.repository

import eterea.core.api.rest.kotlin.model.RegistroCae
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface RegistroCaeRepository : JpaRepository<RegistroCae, Long> {
    fun findByComprobanteIdAndPuntoVentaAndNumeroComprobante(comprobanteId: Int, puntoVenta: Int, numeroComprobante: Long): Optional<RegistroCae?>?

}