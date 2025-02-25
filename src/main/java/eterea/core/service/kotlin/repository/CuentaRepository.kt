package eterea.core.service.kotlin.repository

import eterea.core.service.kotlin.model.Cuenta
import org.springframework.data.jpa.repository.JpaRepository
import java.math.BigDecimal
import java.util.Optional

interface CuentaRepository : JpaRepository<Cuenta, Long> {

    fun findAllByCuentaMaestroGreaterThan(zero: BigDecimal): List<Cuenta>
    fun findByNumeroCuenta(numeroCuenta: Long): Optional<Cuenta?>?
    fun findByCuentaMaestro(cuentaMaestro: BigDecimal): Optional<Cuenta?>?

}