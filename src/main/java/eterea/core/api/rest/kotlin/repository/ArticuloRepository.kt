package eterea.core.api.rest.kotlin.repository

import eterea.core.api.rest.kotlin.model.Articulo
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ArticuloRepository : JpaRepository<Articulo, String> {

    fun findTop50ByDescripcionLikeOrderByDescripcion(descripcion: String?): List<Articulo?>?

    fun findByAutoNumericoId(autoNumericoId: Long?): Optional<Articulo?>?

    fun findByArticuloId(articuloId: String?): Optional<Articulo?>?

}