package eterea.core.service.kotlin.model

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.json.JsonMapper
import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "articulos")
data class Articulo(

    @Id
    @Column(name = "codigo")
    var articuloId: String? = null,

    @Column(name = "art_neg_id")
    var negocioId: Int? = null,

    var descripcion: String = "",

    @Column(name = "art_voucher")
    var leyendaVoucher: String = "",

    @Column(name = "precioventasiniva")
    var precioVentaSinIva: BigDecimal = BigDecimal.ZERO,

    @Column(name = "preciounitario")
    var precioVentaConIva: BigDecimal = BigDecimal.ZERO,

    @Column(name = "cgocontable")
    var cuentaVentas: Long? = null,

    @Column(name = "cgocontablecompras")
    var cuentaCompras: Long? = null,

    @Column(name = "cgocontablegastos")
    var cuentaGastos: Long? = null,

    @Column(name = "cgocentro")
    var centroStockId: Int? = null,

    @Column(name = "cgorubro")
    var rubroId: Long? = null,

    @Column(name = "cgosubrubro")
    var subRubroId: Long? = null,

    @Column(name = "preciocompra")
    var precioCompra: BigDecimal = BigDecimal.ZERO,

    var iva105: Byte = 0,

    @Column(name = "art_compraneto")
    var precioCompraNeto: BigDecimal = BigDecimal.ZERO,

    var exento: Byte = 0,

    @Column(name = "stockminimo")
    var stockMinimo: Long = 0L,

    @Column(name = "stockoptimo")
    var stockOptimo: Long = 0L,

    @Column(name = "bloqueocompras")
    var bloqueoCompras: Byte = 0,

    @Column(name = "bloqueostock")
    var bloqueoStock: Byte = 0,

    @Column(name = "bloqueoventas")
    var bloqueoVentas: Byte = 0,

    @Column(name = "art_ume_id")
    var unidadMedidaId: Long? = null,

    @Column(name = "art_condecimales")
    var conDecimales: Byte = 0,

    @Column(name = "art_ventas")
    var ventas: Byte = 0,

    @Column(name = "art_compras")
    var compras: Byte = 0,

    @Column(name = "umedida")
    var unidadMedida: String = "",

    @Column(name = "art_con_id")
    var conversionId: Int? = null,

    @Column(name = "art_ventasinstock")
    var ventaSinStock: Byte = 0,

    @Column(name = "art_controlstock")
    var controlaStock: Byte = 0,

    @Column(name = "art_asientocostos")
    var asientoCostos: Byte = 0,

    @Column(name = "art_maskbal")
    var mascaraBalanza: String = "",

    @Column(name = "art_habingreso")
    var habilitaIngreso: Byte = 0,

    @Column(name = "art_comision")
    var comision: BigDecimal = BigDecimal.ZERO,

    @Column(name = "art_pre_ID")
    var prestadorId: Int? = null,

    @Column(name = "clave")
    var autoNumericoId: Long? = null

) : Auditable() {

    fun jsonify(): String {
        try {
            return JsonMapper
                .builder()
                .findAndAddModules()
                .build()
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(this)
        } catch (e: JsonProcessingException) {
            return "jsonify error ${e.message}"
        }
    }

    companion object {
        fun builder(): Builder = Builder()
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

        fun build() = Articulo(
            articuloId = articuloId,
            negocioId = negocioId,
            descripcion = descripcion,
            leyendaVoucher = leyendaVoucher,
            precioVentaSinIva = precioVentaSinIva,
            precioVentaConIva = precioVentaConIva,
            cuentaVentas = cuentaVentas,
            cuentaCompras = cuentaCompras,
            cuentaGastos = cuentaGastos,
            centroStockId = centroStockId,
            rubroId = rubroId,
            subRubroId = subRubroId,
            precioCompra = precioCompra,
            iva105 = iva105,
            precioCompraNeto = precioCompraNeto,
            exento = exento,
            stockMinimo = stockMinimo,
            stockOptimo = stockOptimo,
            bloqueoCompras = bloqueoCompras,
            bloqueoStock = bloqueoStock,
            bloqueoVentas = bloqueoVentas,
            unidadMedidaId = unidadMedidaId,
            conDecimales = conDecimales,
            ventas = ventas,
            compras = compras,
            unidadMedida = unidadMedida,
            conversionId = conversionId,
            ventaSinStock = ventaSinStock,
            controlaStock = controlaStock,
            asientoCostos = asientoCostos,
            mascaraBalanza = mascaraBalanza,
            habilitaIngreso = habilitaIngreso,
            comision = comision,
            prestadorId = prestadorId,
            autoNumericoId = autoNumericoId
        )
    }
}
