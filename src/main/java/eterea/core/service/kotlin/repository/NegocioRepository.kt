package eterea.core.service.kotlin.repository

import eterea.core.service.kotlin.model.Negocio
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface NegocioRepository : JpaRepository<Negocio, Int> {

    fun findByNegocioId(negocioId: Int): Optional<Negocio?>?

    fun findAllByCopyArticulo(copyArticulo: Byte): List<Negocio?>?

}