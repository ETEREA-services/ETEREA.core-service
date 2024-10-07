package eterea.core.service.kotlin.repository

import eterea.core.service.kotlin.model.CuentaMovimiento
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.math.BigDecimal
import java.time.OffsetDateTime
import java.util.*

@Repository
interface CuentaMovimientoRepository : JpaRepository<CuentaMovimiento?, Long?> {

    fun findByCuentaMovimientoId(cuentaMovimientoId: Long?): Optional<CuentaMovimiento?>?
    fun findFirstByFechaAndOrdenOrderByItemDesc(fecha: OffsetDateTime?, orden: Int?): Optional<CuentaMovimiento?>?
    fun findFirstByFechaOrderByOrdenDesc(fecha: OffsetDateTime?): Optional<CuentaMovimiento?>?

    @Query("SELECT COALESCE(SUM(cm.importe), 0) FROM CuentaMovimiento cm WHERE cm.fecha BETWEEN :desde AND :hasta AND cm.numeroCuenta = :numeroCuenta AND cm.debita = :debita")
    fun calculateTotalByNumeroCuentaAndDebitaAndFechaBetween(numeroCuenta: Long, debita: Int, desde: OffsetDateTime, hasta: OffsetDateTime): BigDecimal

    @Query("SELECT COALESCE(SUM(cm.importe), 0) FROM CuentaMovimiento cm WHERE cm.fecha BETWEEN :desde AND :hasta AND cm.numeroCuenta = :numeroCuenta AND cm.debita = :debita AND cm.inflacion = :inflacion")
    fun calculateTotalByNumeroCuentaAndDebitaAndInflacionAndFechaBetween(
        numeroCuenta: Long,
        debita: Int,
        inflacion: Int,
        desde: OffsetDateTime,
        hasta: OffsetDateTime
    ): BigDecimal

}
