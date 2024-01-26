package eterea.core.api.rest.kotlin.model.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal

data class FacturacionDTO(

    @JsonProperty(value = "tipo_documento")
    var tipoDocumento: Int = 0,

    var documento: String = "0",

    @JsonProperty(value = "tipo_afip")
    var tipoAfip: Int = 0,

    @JsonProperty(value = "punto_venta")
    var puntoVenta: Int = 0,
    var total: BigDecimal = BigDecimal.ZERO,
    var exento: BigDecimal = BigDecimal.ZERO,
    var neto: BigDecimal = BigDecimal.ZERO,
    var neto105: BigDecimal = BigDecimal.ZERO,
    var iva: BigDecimal = BigDecimal.ZERO,
    var iva105: BigDecimal = BigDecimal.ZERO,
    var resultado: String = "",
    var cae: String = "",

    @JsonProperty(value = "vencimiento_cae")
    var vencimientoCae: String = "",

    @JsonProperty(value = "numero_comprobante")
    var numeroComprobante: Long = 0

) {
    data class Builder(
        var tipoDocumento: Int = 0,
        var documento: String = "0",
        var tipoAfip: Int = 0,
        var puntoVenta: Int = 0,
        var total: BigDecimal = BigDecimal.ZERO,
        var exento: BigDecimal = BigDecimal.ZERO,
        var neto: BigDecimal = BigDecimal.ZERO,
        var neto105: BigDecimal = BigDecimal.ZERO,
        var iva: BigDecimal = BigDecimal.ZERO,
        var iva105: BigDecimal = BigDecimal.ZERO,
        var resultado: String = "",
        var cae: String = "",
        var vencimientoCae: String = "",
        var numeroComprobante: Long = 0
    ) {
        fun tipoDocumento(tipoDocumento: Int) = apply { this.tipoDocumento = tipoDocumento }
        fun documento(documento: String) = apply { this.documento = documento }
        fun tipoAfip(tipoAfip: Int) = apply { this.tipoAfip = tipoAfip }
        fun puntoVenta(puntoVenta: Int) = apply { this.puntoVenta = puntoVenta }
        fun total(total: BigDecimal) = apply { this.total = total }
        fun exento(exento: BigDecimal) = apply { this.exento = exento }
        fun neto(neto: BigDecimal) = apply { this.neto = neto }
        fun neto105(neto105: BigDecimal) = apply { this.neto105 = neto105 }
        fun iva(iva: BigDecimal) = apply { this.iva = iva }
        fun iva105(iva105: BigDecimal) = apply { this.iva105 = iva105 }
        fun resultado(resultado: String) = apply { this.resultado = resultado }
        fun cae(cae: String) = apply { this.cae = cae }
        fun vencimientoCae(vencimientoCae: String) = apply { this.vencimientoCae = vencimientoCae }
        fun numeroComprobate(numeroComprobante: Long) = apply {this.numeroComprobante = numeroComprobante}

        fun build() = FacturacionDTO(
            tipoDocumento, documento, tipoAfip, puntoVenta, total, exento,
            neto, neto105, iva, iva105, resultado, cae, vencimientoCae, numeroComprobante
        )
    }
}

