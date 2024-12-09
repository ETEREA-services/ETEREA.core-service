package eterea.core.service.kotlin.repository

import eterea.core.service.kotlin.model.Cuenta
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface CuentaRepository : JpaRepository<Cuenta, Long> {

    fun findByNumeroCuenta(numeroCuenta: Long): Optional<Cuenta?>?

}