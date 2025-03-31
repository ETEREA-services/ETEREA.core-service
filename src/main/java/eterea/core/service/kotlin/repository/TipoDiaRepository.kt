package eterea.core.service.kotlin.repository

import eterea.core.service.kotlin.model.TipoDia
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface TipoDiaRepository : JpaRepository<TipoDia, Long> {
   fun findByNombre(nombre: String): Optional<TipoDia>;
}