package eterea.core.service.kotlin.repository

import eterea.core.service.kotlin.model.Articulo
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ArticuloRepository : JpaRepository<Articulo, String> {

    fun findTop50ByDescripcionLikeOrderByDescripcion(descripcion: String?): List<Articulo?>?

    fun findByAutoNumericoId(autoNumericoId: Long?): Optional<Articulo?>?

    fun findByArticuloId(articuloId: String?): Optional<Articulo?>?

    fun findByMascaraBalanza(mascaraBalanza: String?): Optional<Articulo?>?

    @Query("""
        SELECT 
            a
        FROM 
            Articulo a
            JOIN ProductoArticulo pa 
                ON a.articuloId = pa.articuloId
        WHERE
            pa.productoId = :productoId
    """)
    fun findAllByProductoId(productoId: Int): List<Articulo>

    fun findAllByProductoIdIn(productoIds: List<Int>): List<Articulo>


}