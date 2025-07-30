package eterea.core.service.kotlin.model.dto

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.json.JsonMapper
import eterea.core.service.kotlin.model.CuentaMovimiento
import eterea.core.service.kotlin.model.ValorMovimiento

data class TransferenciaWrapperDto(

    var transferencia: TransferenciaDto? = null,
    var valorMovimientos: List<ValorMovimiento?>? = null,
    var cuentaMovimientos: List<CuentaMovimiento?>? = null

) {

    fun jsonify(): String {
        try {
            return JsonMapper
                .builder()
                .findAndAddModules()
                .build()
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(this)
        } catch (e : JsonProcessingException) {
            return "jsonify error -> ${e.message}"
        }
    }

    class Builder {
        private var transferencia: TransferenciaDto? = null
        private var valorMovimientos: List<ValorMovimiento?>? = null
        private var cuentaMovimientos: List<CuentaMovimiento?>? = null

        fun transferencia(transferencia: TransferenciaDto?) = apply { this.transferencia = transferencia }
        fun valorMovimientos(valorMovimientos: List<ValorMovimiento?>?) = apply { this.valorMovimientos = valorMovimientos }
        fun cuentaMovimientos(cuentaMovimientos: List<CuentaMovimiento?>?) = apply { this.cuentaMovimientos = cuentaMovimientos }

        fun build() = TransferenciaWrapperDto(
            transferencia,
            valorMovimientos,
            cuentaMovimientos
        )
    }

    companion object {
        fun builder() = Builder()
    }

}
