package eterea.core.service.kotlin.repository

import eterea.core.service.model.CuentaMovimiento
import jakarta.transaction.Transactional
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.math.BigDecimal
import java.time.OffsetDateTime
import java.util.*

@Repository
interface CuentaMovimientoRepository : JpaRepository<CuentaMovimiento, Long> {

    fun findAllByFechaAndOrden(fechaContable: OffsetDateTime, ordenContable: Int): List<CuentaMovimiento>
    fun findAllByFechaBetween(fechaDesde: OffsetDateTime, fechaHasta: OffsetDateTime): List<CuentaMovimiento>
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

    @Modifying
    @Transactional
    @Query("""
        DELETE FROM CuentaMovimiento cm
        WHERE cm.fecha = :fechaContable AND cm.orden = :ordenContable
    """)
    fun deleteAllByFechaAndOrden(fechaContable: OffsetDateTime, ordenContable: Int)

}
