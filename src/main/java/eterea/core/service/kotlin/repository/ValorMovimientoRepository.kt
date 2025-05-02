package eterea.core.service.kotlin.repository

import eterea.core.service.kotlin.model.ValorMovimiento
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.time.OffsetDateTime
import java.util.Optional

@Repository
interface ValorMovimientoRepository : JpaRepository<ValorMovimiento, Int> {

    fun findByValorMovimientoId(valorMovimientoId: Long): Optional<ValorMovimiento?>?

    @Query("""
        SELECT vm
        FROM ValorMovimiento vm
        WHERE vm.fechaContable >= :desde AND vm.fechaContable <= :hasta
            AND (CASE WHEN :cierreCajaOnly = TRUE
               THEN (vm.cierreCajaId != 0 AND vm.cierreCajaId IS NOT NULL)
               ELSE TRUE
               END)
            AND (CASE WHEN :ingresosOnly = TRUE
               THEN (vm.importe > 0)
               ELSE TRUE
               END)
    """)
    fun findAllByFechaContableBetween(
        desde: OffsetDateTime,
        hasta: OffsetDateTime,
        cierreCajaOnly: Boolean,
        ingresosOnly: Boolean
    ): List<ValorMovimiento>

    fun findAllByFechaContableAndOrdenContable(
        fechaContable: OffsetDateTime,
        ordenContable: Int
    ): List<ValorMovimiento?>?

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("""
        DELETE FROM ValorMovimiento vm
        WHERE vm.fechaContable = :fechaContable AND vm.ordenContable = :ordenContable
    """)
    fun deleteAllByFechaContableAndOrdenContable(fechaContable: OffsetDateTime, ordenContable: Int)

}