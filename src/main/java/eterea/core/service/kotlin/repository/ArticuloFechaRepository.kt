package eterea.core.service.kotlin.repository

import eterea.core.service.kotlin.model.ArticuloFecha
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.time.OffsetDateTime
import java.util.*

@Repository
interface ArticuloFechaRepository : JpaRepository<ArticuloFecha, Long> {
    fun findByArticuloIdAndFecha(articuloId: String?, fecha: OffsetDateTime?): Optional<ArticuloFecha?>?

    fun findByArticuloFechaId(articuloFechaId: Long?): Optional<ArticuloFecha?>?
    fun findAllByArticuloIdAndFechaBetween(
        articuloId: String,
        fechaInicio: OffsetDateTime,
        fechaFin: OffsetDateTime
    ): List<ArticuloFecha?>?

}
