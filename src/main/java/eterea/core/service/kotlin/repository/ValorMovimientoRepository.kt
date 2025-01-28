package eterea.core.service.kotlin.repository

import eterea.core.service.kotlin.model.ValorMovimiento
import eterea.core.service.model.dto.CobroInternoDto
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
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


    @Query("""
        SELECT new eterea.core.service.model.dto.CobroInternoDto(
            vm.valorMovimientoId,
            vm.cierreCajaId,
            vm.negocioId,
            vm.fechaContable,
            vm.importe,
            vm.clienteMovimientoId,
            vm.clienteId,
            vm.numeroCuenta,
            cm.puntoVenta,
            cli.cuit,
            cli.razonSocial,
            cli.nombre,
            val.concepto,
            comp.comprobanteId,
            comp.descripcion
        )
        FROM
            ValorMovimiento vm
            JOIN ClienteMovimiento cm
                ON vm.clienteMovimientoId = cm.clienteMovimientoId
            JOIN Cliente cli
                ON cm.clienteId = cli.clienteId
            JOIN Valor val
                ON vm.valor.valorId = val.valorId
            JOIN Comprobante comp
                ON vm.comprobanteId = comp.comprobanteId
        WHERE
            vm.fechaContable >= :desde AND vm.fechaContable < :hasta
    """)
    fun findAllCobroInternoByFechaContableBetween(desde: OffsetDateTime, hasta: OffsetDateTime): List<CobroInternoDto>

}