package eterea.core.api.rest.kotlin.repository

import eterea.core.api.rest.kotlin.model.Negocio
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface NegocioRepository : JpaRepository<Negocio, Int> {

    fun findByNegocioId(negocioId: Int): Optional<Negocio?>?

}