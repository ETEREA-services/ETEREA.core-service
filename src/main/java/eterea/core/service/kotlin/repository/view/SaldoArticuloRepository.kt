package eterea.core.service.kotlin.repository.view

import eterea.core.service.kotlin.model.view.SaldoArticulo
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface SaldoArticuloRepository : JpaRepository<SaldoArticulo, String> {

    fun findAllByCentroStockIdAndArticuloIdIn(
        centroStockId: Int,
        articuloIds: MutableList<String>
    ): List<SaldoArticulo>

    fun findByCentroStockIdAndArticuloId(centroStockId: Int, articuloId: String): Optional<SaldoArticulo?>?

}