package eterea.core.service.kotlin.repository

import eterea.core.service.kotlin.model.ReservaComentario
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ReservaComentarioRepository : JpaRepository<ReservaComentario, Long> {

}
