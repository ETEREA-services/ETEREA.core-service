package eterea.core.service.kotlin.repository

import eterea.core.service.kotlin.model.ConceptoFacturado
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface ConceptoFacturadoRepository : JpaRepository<ConceptoFacturado, Long> {

    fun findTopByArticuloMovimientoId(articuloMovimientoId: Long): Optional<ConceptoFacturado?>?

}
