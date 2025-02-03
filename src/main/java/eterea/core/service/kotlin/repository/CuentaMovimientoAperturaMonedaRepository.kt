package eterea.core.service.kotlin.repository

import eterea.core.service.kotlin.model.CuentaMovimientoAperturaMoneda
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.time.OffsetDateTime
import java.util.UUID

interface CuentaMovimientoAperturaMonedaRepository : JpaRepository<CuentaMovimientoAperturaMoneda?, UUID?> {

    @Modifying
    @Query(nativeQuery = true, value = """
        INSERT INTO cuenta_movimiento_apertura_moneda (
            cuenta_movimiento_apertura_moneda_id, fecha, orden, item, moneda_id, importe, created
        )
        SELECT 
            UNHEX(REPLACE(UUID(), '-', '')),
            m.mca_fecha, 
            m.mca_nrocomp, 
            m.mca_item, 
            c.moneda_id, 
            ROUND(m.mca_Importe / c.cotizacion, 4), 
            NOW()
        FROM movconapertura m
        JOIN moneda_cotizacion c ON m.mca_fecha = c.fecha
        WHERE m.mca_fecha BETWEEN :fechaDesde AND :fechaHasta
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
        DELETE cmm FROM cuenta_movimiento_apertura_moneda cmm
        WHERE cmm.fecha BETWEEN :fechaDesde AND :fechaHasta
        AND cmm.moneda_id = :monedaId
        AND NOT EXISTS (
            SELECT 1 FROM movconapertura m 
            WHERE m.mca_fecha = cmm.fecha 
            AND m.mca_nrocomp = cmm.orden 
            AND m.mca_item = cmm.item
        )
    """)
    fun deleteOrphanedRecords(
        @Param("monedaId") monedaId: Int,
        @Param("fechaDesde") fechaDesde: OffsetDateTime,
        @Param("fechaHasta") fechaHasta: OffsetDateTime
    ): Int

}