package eterea.core.service.kotlin.model.dto

import java.math.BigDecimal

data class CuentaDto(

    var numeroCuenta: Long? = null,
    var nombre: String? = null,
    var negocioId: Int? = null,
    var integra: Byte? = null,
    var transfiere: Byte? = null,
    var movimientoStock: Byte? = null,
    var cuentaMaestro: BigDecimal = BigDecimal.ZERO,
    var grado: Int? = null,
    var grado1: Long? = null,
    var grado2: Long? = null,
    var grado3: Long? = null,
    var grado4: Long? = null,
    var ventas: Byte? = null,
    var compras: Byte? = null,
    var gastos: Byte? = null

)
