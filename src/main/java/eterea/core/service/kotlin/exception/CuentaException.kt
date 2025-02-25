package eterea.core.service.kotlin.exception

import java.math.BigDecimal

class CuentaException : RuntimeException {
    constructor(numeroCuenta: Long) : super("No existe la cuenta $numeroCuenta")
    constructor(cuentaMaestro: BigDecimal) : super("No existe la cuenta maestra $cuentaMaestro")
}