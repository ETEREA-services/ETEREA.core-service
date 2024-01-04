package eterea.core.api.rest.kotlin.repository

import eterea.core.api.rest.kotlin.model.ConceptoFacturado
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface IConceptoFacturadoRepository : JpaRepository<ConceptoFacturado?, Long?> {

    fun findByArticuloMovimientoId(articuloMovimientoId: Long): Optional<ConceptoFacturado?>?

}
