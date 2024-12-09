package eterea.core.service.kotlin.exception

class CuentaException(numeroCuenta: Long) : RuntimeException("No existe la cuenta $numeroCuenta")