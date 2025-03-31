package eterea.core.service.kotlin.repository

import eterea.core.service.kotlin.model.TipoDia
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TipoDiaRepository : JpaRepository<TipoDia, Long> {

}