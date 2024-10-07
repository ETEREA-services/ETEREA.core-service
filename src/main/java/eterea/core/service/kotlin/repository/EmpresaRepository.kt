package eterea.core.service.kotlin.repository

import eterea.core.service.kotlin.model.Empresa
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface EmpresaRepository : JpaRepository<Empresa, Int> {

    fun findTopByOrderByEmpresaIdDesc(): Optional<Empresa?>?

}