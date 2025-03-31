package eterea.core.service.kotlin.repository

import eterea.core.service.kotlin.model.TipoPax
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TipoPaxRepository : JpaRepository<TipoPax, Long> {
}
