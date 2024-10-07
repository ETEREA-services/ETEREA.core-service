package eterea.core.service.kotlin.repository

import eterea.core.service.kotlin.model.Valor
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface ValorRepository : JpaRepository<Valor, Int> {

    fun findByValorId(valorId: Int): Optional<Valor?>?

}