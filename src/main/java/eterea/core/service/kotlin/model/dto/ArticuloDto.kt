package eterea.core.service.kotlin.model.dto

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.json.JsonMapper
import java.math.BigDecimal

data class ArticuloDto (

    val articuloId: String?,
    val negocioId: Int?,
    var descripcion: String,
    val leyendaVoucher: String,
    var precioVentaSinIva: BigDecimal,
    var precioVentaConIva: BigDecimal,
    val cuentaVentas: Long?,
    val cuentaCompras: Long?,
    val cuentaGastos: Long?,
    val centroStockId: Int?,
    val rubroId: Long?,
    val subRubroId: Long?,
    val precioCompra: BigDecimal,
    val iva105: Byte,
    val precioCompraNeto: BigDecimal,
    val exento: Byte,
    val stockMinimo: Long,
    val stockOptimo: Long,
    val bloqueoCompras: Byte,
    val bloqueoStock: Byte,
    val bloqueoVentas: Byte,
    val unidadMedidaId: Long?,
    val conDecimales: Byte,
    val ventas: Byte,
    val compras: Byte,
    val unidadMedida: String,
    val conversionId: Int?,
    val ventaSinStock: Byte,
    val controlaStock: Byte,
    val asientoCostos: Byte,
    val mascaraBalanza: String,
    val habilitaIngreso: Byte,
    val comision: BigDecimal,
    val prestadorId: Int?,
    val autoNumericoId: Long?
) {

    fun jsonify(): String {
        try {
            return JsonMapper
                .builder()
                .findAndAddModules()
                .build()
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(this)
        } catch (e: JsonProcessingException) {
            return "jsonify error -> ${e.message}"
        }
    }

    class Builder {
        private var articuloId: String? = null
        private var negocioId: Int? = null
        private var descripcion: String = ""
        private var leyendaVoucher: String = ""
        private var precioVentaSinIva: BigDecimal = BigDecimal.ZERO
        private var precioVentaConIva: BigDecimal = BigDecimal.ZERO
        private var cuentaVentas: Long? = null
        private var cuentaCompras: Long? = null
        private var cuentaGastos: Long? = null
        private var centroStockId: Int? = null
        private var rubroId: Long? = null
        private var subRubroId: Long? = null
        private var precioCompra: BigDecimal = BigDecimal.ZERO
        private var iva105: Byte = 0
        private var precioCompraNeto: BigDecimal = BigDecimal.ZERO
        private var exento: Byte = 0
        private var stockMinimo: Long = 0L
        private var stockOptimo: Long = 0L
        private var bloqueoCompras: Byte = 0
        private var bloqueoStock: Byte = 0
        private var bloqueoVentas: Byte = 0
        private var unidadMedidaId: Long? = null
        private var conDecimales: Byte = 0
        private var ventas: Byte = 0
        private var compras: Byte = 0
        private var unidadMedida: String = ""
        private var conversionId: Int? = null
        private var ventaSinStock: Byte = 0
        private var controlaStock: Byte = 0
        private var asientoCostos: Byte = 0
        private var mascaraBalanza: String = ""
        private var habilitaIngreso: Byte = 0
        private var comision: BigDecimal = BigDecimal.ZERO
        private var prestadorId: Int? = null
        private var autoNumericoId: Long? = null

        fun articuloId(articuloId: String?) = apply { this.articuloId = articuloId }
        fun negocioId(negocioId: Int?) = apply { this.negocioId = negocioId }
        fun descripcion(descripcion: String) = apply { this.descripcion = descripcion }
        fun leyendaVoucher(leyendaVoucher: String) = apply { this.leyendaVoucher = leyendaVoucher }
        fun precioVentaSinIva(precioVentaSinIva: BigDecimal) = apply { this.precioVentaSinIva = precioVentaSinIva }
        fun precioVentaConIva(precioVentaConIva: BigDecimal) = apply { this.precioVentaConIva = precioVentaConIva }
        fun cuentaVentas(cuentaVentas: Long?) = apply { this.cuentaVentas = cuentaVentas }
        fun cuentaCompras(cuentaCompras: Long?) = apply { this.cuentaCompras = cuentaCompras }
        fun cuentaGastos(cuentaGastos: Long?) = apply { this.cuentaGastos = cuentaGastos }
        fun centroStockId(centroStockId: Int?) = apply { this.centroStockId = centroStockId }
        fun rubroId(rubroId: Long?) = apply { this.rubroId = rubroId }
        fun subRubroId(subRubroId: Long?) = apply { this.subRubroId = subRubroId }
        fun precioCompra(precioCompra: BigDecimal) = apply { this.precioCompra = precioCompra }
        fun iva105(iva105: Byte) = apply { this.iva105 = iva105 }
        fun precioCompraNeto(precioCompraNeto: BigDecimal) = apply { this.precioCompraNeto = precioCompraNeto }
        fun exento(exento: Byte) = apply { this.exento = exento }
        fun stockMinimo(stockMinimo: Long) = apply { this.stockMinimo = stockMinimo }
        fun stockOptimo(stockOptimo: Long) = apply { this.stockOptimo = stockOptimo }
        fun bloqueoCompras(bloqueoCompras: Byte) = apply { this.bloqueoCompras = bloqueoCompras }
        fun bloqueoStock(bloqueoStock: Byte) = apply { this.bloqueoStock = bloqueoStock }
        fun bloqueoVentas(bloqueoVentas: Byte) = apply { this.bloqueoVentas = bloqueoVentas }
        fun unidadMedidaId(unidadMedidaId: Long?) = apply { this.unidadMedidaId = unidadMedidaId }
        fun conDecimales(conDecimales: Byte) = apply { this.conDecimales = conDecimales }
        fun ventas(ventas: Byte) = apply { this.ventas = ventas }
        fun compras(compras: Byte) = apply { this.compras = compras }
        fun unidadMedida(unidadMedida: String) = apply { this.unidadMedida = unidadMedida }
        fun conversionId(conversionId: Int?) = apply { this.conversionId = conversionId }
        fun ventaSinStock(ventaSinStock: Byte) = apply { this.ventaSinStock = ventaSinStock }
        fun controlaStock(controlaStock: Byte) = apply { this.controlaStock = controlaStock }
        fun asientoCostos(asientoCostos: Byte) = apply { this.asientoCostos = asientoCostos }
        fun mascaraBalanza(mascaraBalanza: String) = apply { this.mascaraBalanza = mascaraBalanza }
        fun habilitaIngreso(habilitaIngreso: Byte) = apply { this.habilitaIngreso = habilitaIngreso }
        fun comision(comision: BigDecimal) = apply { this.comision = comision }
        fun prestadorId(prestadorId: Int?) = apply { this.prestadorId = prestadorId }
        fun autoNumericoId(autoNumericoId: Long?) = apply { this.autoNumericoId = autoNumericoId }

        fun build() = ArticuloDto(
            articuloId, negocioId, descripcion, leyendaVoucher, precioVentaSinIva,
            precioVentaConIva, cuentaVentas, cuentaCompras, cuentaGastos, centroStockId,
            rubroId, subRubroId, precioCompra, iva105, precioCompraNeto, exento,
            stockMinimo, stockOptimo, bloqueoCompras, bloqueoStock, bloqueoVentas,
            unidadMedidaId, conDecimales, ventas, compras, unidadMedida, conversionId,
            ventaSinStock, controlaStock, asientoCostos, mascaraBalanza, habilitaIngreso,
            comision, prestadorId, autoNumericoId
        )
    }

    companion object {
        fun builder() = Builder()
    }
}