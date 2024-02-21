package eterea.core.api.rest.kotlin.repository

import eterea.core.api.rest.kotlin.model.ArticuloFecha
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.time.OffsetDateTime
import java.util.*

@Repository
interface ArticuloFechaRepository : JpaRepository<ArticuloFecha?, Long?> {
    fun findByArticuloIdAndFecha(articuloId: String?, fecha: OffsetDateTime?): Optional<ArticuloFecha?>?

    fun findByArticuloFechaId(articuloFechaId: Long?): Optional<ArticuloFecha?>?

}
