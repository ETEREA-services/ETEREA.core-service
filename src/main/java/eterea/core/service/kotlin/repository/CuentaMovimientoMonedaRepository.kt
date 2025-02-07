package eterea.core.service.kotlin.repository

import eterea.core.service.kotlin.model.CuentaMovimientoMoneda
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.time.OffsetDateTime
import java.util.UUID

interface CuentaMovimientoMonedaRepository : JpaRepository<CuentaMovimientoMoneda?, UUID?> {

    @Modifying
    @Query(nativeQuery = true, value = """
        INSERT INTO cuenta_movimiento_moneda (
            cuenta_movimiento_moneda_id, fecha, orden, item, moneda_id, importe, created
        )
        SELECT 
            UNHEX(REPLACE(UUID(), '-', '')),
            m.fecha, 
            m.nrocomp, 
            m.item, 
            c.moneda_id, 
            ROUND(m.Importe / c.cotizacion, 5), 
            NOW()
        FROM movcon m
        JOIN moneda_cotizacion c ON m.fecha = c.fecha
        WHERE m.fecha BETWEEN :fechaDesde AND :fechaHasta
        AND c.moneda_id = :monedaId
        ON DUPLICATE KEY UPDATE 
            fecha = VALUES(fecha),
            orden = VALUES(orden),
            item = VALUES(item),
            moneda_id = VALUES(moneda_id),
            importe = VALUES(importe),
            created = VALUES(created)
    """)
    fun insertOrUpdateMovimientosCotizados(
        @Param("monedaId") monedaId: Int,
        @Param("fechaDesde") fechaDesde: OffsetDateTime,
        @Param("fechaHasta") fechaHasta: OffsetDateTime
    )

    @Modifying
    @Query(nativeQuery = true, value = """
        DELETE cmm FROM cuenta_movimiento_moneda cmm
        WHERE cmm.fecha BETWEEN :fechaDesde AND :fechaHasta
        AND cmm.moneda_id = :monedaId
        AND NOT EXISTS (
            SELECT 1 FROM movcon m 
            WHERE m.fecha = cmm.fecha 
            AND m.nrocomp = cmm.orden 
            AND m.item = cmm.item
        )
    """)
    fun deleteOrphanedRecords(
        @Param("monedaId") monedaId: Int,
        @Param("fechaDesde") fechaDesde: OffsetDateTime,
        @Param("fechaHasta") fechaHasta: OffsetDateTime
    ): Int

}