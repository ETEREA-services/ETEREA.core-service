package eterea.core.service.kotlin.repository

import eterea.core.service.kotlin.model.ArticuloBarra
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface ArticuloBarraRepository : JpaRepository<ArticuloBarra, String> {

    fun findByCodigoBarras(codigobarras: String): Optional<ArticuloBarra?>?

}