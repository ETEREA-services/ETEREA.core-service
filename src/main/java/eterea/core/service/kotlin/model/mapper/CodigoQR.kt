package eterea.core.service.kotlin.model.mapper

import java.math.BigDecimal

data class CodigoQR(

    var ver: Int = 1,
    var fecha: String = "",
    var cuit: String = "",
    var ptoVta: Int = 0,
    var tipoCmp: Int = 0,
    var nroCmp: Long = 0,
    var importe: BigDecimal = BigDecimal.ZERO,
    var moneda: String = "",
    var ctz: Int = 1,
    var tipoDocRec: Int = 0,
    var nroDocRec: BigDecimal = BigDecimal.ZERO,
    var tipoCodAut: String = "E",
    var codAut: String = ""
)
