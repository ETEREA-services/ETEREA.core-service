package eterea.core.api.rest.kotlin.repository

import eterea.core.api.rest.kotlin.model.CuentaMovimiento
import eterea.core.api.rest.repository.ICuentaMovimientoRepositoryCustom
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.time.OffsetDateTime
import java.util.*

@Repository
interface CuentaMovimientoRepository : JpaRepository<CuentaMovimiento?, Long?>, ICuentaMovimientoRepositoryCustom {
    fun findByCuentaMovimientoId(cuentaMovimientoId: Long?): Optional<CuentaMovimiento?>?

    fun findFirstByFechaAndOrdenOrderByItemDesc(fecha: OffsetDateTime?, orden: Int?): Optional<CuentaMovimiento?>?

    fun findFirstByFechaOrderByOrdenDesc(fecha: OffsetDateTime?): Optional<CuentaMovimiento?>?
}
