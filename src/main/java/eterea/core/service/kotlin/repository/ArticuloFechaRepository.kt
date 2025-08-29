package eterea.core.service.kotlin.repository

import eterea.core.service.kotlin.model.ArticuloFecha
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal
import java.time.OffsetDateTime
import java.util.*

@Repository
interface ArticuloFechaRepository : JpaRepository<ArticuloFecha?, Long?> {
    fun findByArticuloIdAndFecha(articuloId: String?, fecha: OffsetDateTime?): Optional<ArticuloFecha?>?

    fun findByArticuloFechaId(articuloFechaId: Long?): Optional<ArticuloFecha?>?
    fun findAllByArticuloIdAndFechaBetween(
        articuloId: String,
        fechaInicio: OffsetDateTime,
        fechaFin: OffsetDateTime
    ): List<ArticuloFecha?>?

    @Modifying
    @Transactional
    @Query("UPDATE ArticuloFecha a SET a.precioUsd = :precioUsd, a.precioArs = :precioArs WHERE a.articuloId = :articuloId AND a.fecha = :fecha")
    fun updateIfExists(
        @Param("articuloId") articuloId: String,
        @Param("fecha") fecha: OffsetDateTime,
        @Param("precioUsd") precioUsd: BigDecimal,
        @Param("precioArs") precioArs: BigDecimal
    ): Int

    fun findAllByArticuloIdInAndFechaIn(
        articuloIds: List<String>,
        fechas: List<OffsetDateTime>
    ): List<ArticuloFecha>

}
