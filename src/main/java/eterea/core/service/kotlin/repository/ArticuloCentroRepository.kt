package eterea.core.service.kotlin.repository

import eterea.core.service.kotlin.model.ArticuloCentro
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface ArticuloCentroRepository : JpaRepository<ArticuloCentro, Long> {

    fun findAllByCentroStockIdAndArticuloIdIn(
        centroStockId: Int,
        articuloIds: MutableList<String>
    ): List<ArticuloCentro>

    fun findByCentroStockIdAndArticuloId(centroStockId: Int, articuloId: String): Optional<ArticuloCentro?>?

}