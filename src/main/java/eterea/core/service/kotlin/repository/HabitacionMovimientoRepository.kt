package eterea.core.service.kotlin.repository

import eterea.core.service.kotlin.model.HabitacionMovimiento
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.time.OffsetDateTime


@Repository
interface HabitacionMovimientoRepository : JpaRepository<HabitacionMovimiento, Long> {

    /**
     * Original VB6 SQL:
     * SELECT hm FROM HabitacionMovimiento hm 
     * WHERE 
     *   ((hm.fechaIngreso <= pIngreso) AND (hm.fechaSalida >= pIngreso + 1))
     *   OR ((hm.fechaIngreso <= pSalida - 1) AND (hm.fechaSalida >= pSalida))
     *   OR ((hm.fechaIngreso >= pIngreso) AND (hm.fechaSalida <= pSalida))
     *   AND hm.cgoEstadoRes <> pCodigoBaja
     *   AND hm.cgoHabitacion = pCgoHab
     *   AND hm.clave <> pClaveExcluir
     *
     *   TipoComprobante = 'Z' -> 'De Baja' (Reserva dada de baja)
     */
    @Query("""
        SELECT
         hm
        FROM
         HabitacionMovimiento hm 
        WHERE 
         (
             ((hm.fechaIngreso <= :fechaIngreso) AND (hm.fechaSalida >= FUNCTION('DATE_ADD', :fechaIngreso, 1, 'DAY')))
             OR
             ((hm.fechaIngreso <= FUNCTION('DATE_ADD', :fechaSalida, -1, 'DAY')) AND (hm.fechaSalida >= :fechaSalida))
             OR
             ((hm.fechaIngreso >= :fechaIngreso) AND (hm.fechaSalida <= :fechaSalida))
         )
         AND hm.habitacion.numero = :numeroHabitacion
         AND hm.habitacionMovimientoId != :idExcluir
         AND hm.estadoReserva.tipoComprobante != 'Z'
        """)
    fun findReservasSuperpuestas(
        @Param("numeroHabitacion") numeroHabitacion: Int,
        @Param("fechaIngreso") fechaIngreso: OffsetDateTime,
        @Param("fechaSalida") fechaSalida: OffsetDateTime,
        @Param("idExcluir") idExcluir: Long
    ): List<HabitacionMovimiento>
}
