package eterea.core.service.kotlin.repository

import eterea.core.service.kotlin.model.CuentaMovimientoFirma
import org.springframework.data.jpa.repository.JpaRepository
import java.time.OffsetDateTime
import java.util.Optional

interface CuentaMovimientoFirmaRepository : JpaRepository<CuentaMovimientoFirma, Long> {

    fun findByFechaContableAndOrdenContable(fechaContable: OffsetDateTime, ordenContable: Int): Optional<CuentaMovimientoFirma?>?

}