package eterea.core.service.kotlin.model

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.json.JsonMapper
import jakarta.persistence.*
import java.math.BigDecimal
import java.time.OffsetDateTime

@Entity
@Table(name = "movclie")
data class ClienteMovimiento(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "clave")
    var clienteMovimientoId: Long? = null,

    @Column(name = "cgocomprob")
    var comprobanteId: Int? = null,

    @Column(name = "prefijo")
    var puntoVenta: Int = 0,

    @Column(name = "nrocomprob")
    var numeroComprobante: Long = 0L,

    @Column(name = "fechacomprob")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
    var fechaComprobante: OffsetDateTime? = null,

    @Column(name = "cgoclie")
    var clienteId: Long? = null,

    @Column(name = "mcl_fechavenc")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
    var fechaVencimiento: OffsetDateTime? = null,

    @Column(name = "mcl_neg_id")
    var negocioId: Int? = null,

    @Column(name = "mcl_emp_id")
    var empresaId: Int? = null,

    var importe: BigDecimal = BigDecimal.ZERO,
    var cancelado: BigDecimal = BigDecimal.ZERO,
    var neto: BigDecimal = BigDecimal.ZERO,

    @Column(name = "netocancelado")
    var netoCancelado: BigDecimal = BigDecimal.ZERO,

    @Column(name = "montoiva")
    var montoIva: BigDecimal = BigDecimal.ZERO,

    @Column(name = "montoivarni")
    var montoIvaRni: BigDecimal = BigDecimal.ZERO,

    @Column(name = "reintegroturista")
    var reintegroTurista: BigDecimal = BigDecimal.ZERO,

    @Column(name = "fechareg")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
    var fechaContable: OffsetDateTime? = null,

    @Column(name = "nrocompconta")
    var ordenContable: Int = 0,

    var recibo: Byte = 0,

    @Column(name = "mcl_asignado")
    var asignado: Byte = 0,

    var anulada: Byte = 0,
    var decreto104316: Byte = 0,

    @Column(name = "tipocompro")
    var letraComprobante: String? = null,

    @Column(name = "montoexento")
    var montoExento: BigDecimal = BigDecimal.ZERO,

    @Column(name = "nroreserva")
    var reservaId: Long? = null,

    @Column(name = "ctacte")
    var montoCuentaCorriente: BigDecimal = BigDecimal.ZERO,

    @Column(name = "mcl_cic_id")
    var cierreCajaId: Long = 0,

    @Column(name = "mcl_cir_id")
    var cierreRestaurantId: Long = 0,

    @Column(name = "mcl_nivel")
    var nivel: Int = 0,

    @Column(name = "mcl_eliminar")
    var eliminar: Byte = 0,

    @Column(name = "mcl_ctacte")
    var cuentaCorriente: Byte = 0,

    @Column(name = "mcl_letras")
    var letras: String = "",

    @Column(name = "mcl_cae")
    var cae: String = "",

    @Column(name = "mcl_caevenc")
    var caeVencimiento: String = "",

    @Column(name = "mcl_barras")
    var codigoBarras: String = "",

    @Column(name = "mcl_particip")
    var participacion: BigDecimal = BigDecimal.ZERO,

    @Column(name = "mcl_mon_id")
    var monedaId: Int? = null,

    @Column(name = "mcl_cotiz")
    var cotizacion: BigDecimal = BigDecimal.ZERO,

    var observaciones: String? = null,
    var trackUuid: String? = null,

    @Column(name = "clavev")
    var clienteMovimientoIdSlave: Long = 0,

    @OneToOne(optional = true)
    @JoinColumn(name = "cgocomprob", insertable = false, updatable = false)
    var comprobante: Comprobante? = null,

    @OneToOne(optional = true)
    @JoinColumn(name = "cgoclie", insertable = false, updatable = false)
    var cliente: Cliente? = null,

    @OneToOne(optional = true)
    @JoinColumn(name = "mcl_mon_id", insertable = false, updatable = false)
    var moneda: Moneda? = null

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
            return "jsonify error " + e.message
        }
    }

    class Builder {
        var clienteMovimientoId: Long? = null
        var comprobanteId: Int? = null
        var puntoVenta: Int = 0
        var numeroComprobante: Long = 0L
        var fechaComprobante: OffsetDateTime? = null
        var clienteId: Long? = null
        var fechaVencimiento: OffsetDateTime? = null
        var negocioId: Int? = null
        var empresaId: Int? = null
        var importe: BigDecimal = BigDecimal.ZERO
        var cancelado: BigDecimal = BigDecimal.ZERO
        var neto: BigDecimal = BigDecimal.ZERO
        var netoCancelado: BigDecimal = BigDecimal.ZERO
        var montoIva: BigDecimal = BigDecimal.ZERO
        var montoIvaRni: BigDecimal = BigDecimal.ZERO
        var reintegroTurista: BigDecimal = BigDecimal.ZERO
        var fechaContable: OffsetDateTime? = null
        var ordenContable: Int = 0
        var recibo: Byte = 0
        var asignado: Byte = 0
        var anulada: Byte = 0
        var decreto104316: Byte = 0
        var letraComprobante: String? = null
        var montoExento: BigDecimal = BigDecimal.ZERO
        var reservaId: Long? = null
        var montoCuentaCorriente: BigDecimal = BigDecimal.ZERO
        var cierreCajaId: Long = 0
        var cierreRestaurantId: Long = 0
        var nivel: Int = 0
        var eliminar: Byte = 0
        var cuentaCorriente: Byte = 0
        var letras: String = ""
        var cae: String = ""
        var caeVencimiento: String = ""
        var codigoBarras: String = ""
        var participacion: BigDecimal = BigDecimal.ZERO
        var monedaId: Int? = null
        var cotizacion: BigDecimal = BigDecimal.ZERO
        var observaciones: String? = null
        var trackUuid: String? = null
        var clienteMovimientoIdSlave: Long = 0
        var comprobante: Comprobante? = null
        var cliente: Cliente? = null
        var moneda: Moneda? = null

        fun clienteMovimientoId(clienteMovimientoId: Long?) = apply { this.clienteMovimientoId = clienteMovimientoId }
        fun comprobanteId(comprobanteId: Int?) = apply { this.comprobanteId = comprobanteId }
        fun puntoVenta(puntoVenta: Int) = apply { this.puntoVenta = puntoVenta }
        fun numeroComprobante(numeroComprobante: Long) = apply { this.numeroComprobante = numeroComprobante }
        fun fechaComprobante(fechaComprobante: OffsetDateTime?) = apply { this.fechaComprobante = fechaComprobante }
        fun clienteId(clienteId: Long?) = apply { this.clienteId = clienteId }
        fun fechaVencimiento(fechaVencimiento: OffsetDateTime?) = apply { this.fechaVencimiento = fechaVencimiento }
        fun negocioId(negocioId: Int?) = apply { this.negocioId = negocioId }
        fun empresaId(empresaId: Int?) = apply { this.empresaId = empresaId }
        fun importe(importe: BigDecimal) = apply { this.importe = importe }
        fun cancelado(cancelado: BigDecimal) = apply { this.cancelado = cancelado }
        fun neto(neto: BigDecimal) = apply { this.neto = neto }
        fun netoCancelado(netoCancelado: BigDecimal) = apply { this.netoCancelado = netoCancelado }
        fun montoIva(montoIva: BigDecimal) = apply { this.montoIva = montoIva }
        fun montoIvaRni(montoIvaRni: BigDecimal) = apply { this.montoIvaRni = montoIvaRni }
        fun reintegroTurista(reintegroTurista: BigDecimal) = apply { this.reintegroTurista = reintegroTurista }
        fun fechaContable(fechaContable: OffsetDateTime?) = apply { this.fechaContable = fechaContable }
        fun ordenContable(ordenContable: Int) = apply { this.ordenContable = ordenContable }
        fun recibo(recibo: Byte) = apply { this.recibo = recibo }
        fun asignado(asignado: Byte) = apply { this.asignado = asignado }
        fun anulada(anulada: Byte) = apply { this.anulada = anulada }
        fun decreto104316(decreto104316: Byte) = apply { this.decreto104316 = decreto104316 }
        fun letraComprobante(letraComprobante: String?) = apply { this.letraComprobante = letraComprobante }
        fun montoExento(montoExento: BigDecimal) = apply { this.montoExento = montoExento }
        fun reservaId(reservaId: Long?) = apply { this.reservaId = reservaId }
        fun montoCuentaCorriente(montoCuentaCorriente: BigDecimal) =
            apply { this.montoCuentaCorriente = montoCuentaCorriente }

        fun cierreCajaId(cierreCajaId: Long) = apply { this.cierreCajaId = cierreCajaId }
        fun cierreRestaurantId(cierreRestaurantId: Long) = apply { this.cierreRestaurantId = cierreRestaurantId }
        fun nivel(nivel: Int) = apply { this.nivel = nivel }
        fun eliminar(eliminar: Byte) = apply { this.eliminar = eliminar }
        fun cuentaCorriente(cuentaCorriente: Byte) = apply { this.cuentaCorriente = cuentaCorriente }
        fun letras(letras: String) = apply { this.letras = letras }
        fun cae(cae: String) = apply { this.cae = cae }
        fun caeVencimiento(caeVencimiento: String) = apply { this.caeVencimiento = caeVencimiento }
        fun codigoBarras(codigoBarras: String) = apply { this.codigoBarras = codigoBarras }
        fun participacion(participacion: BigDecimal) = apply { this.participacion = participacion }
        fun monedaId(monedaId: Int?) = apply { this.monedaId = monedaId }
        fun cotizacion(cotizacion: BigDecimal) = apply { this.cotizacion = cotizacion }
        fun observaciones(observaciones: String?) = apply { this.observaciones = observaciones }
        fun trackUuid(trackUuid: String?) = apply { this.trackUuid = trackUuid }
        fun clienteMovimientoIdSlave(clienteMovimientoIdSlave: Long) =
            apply { this.clienteMovimientoIdSlave = clienteMovimientoIdSlave }

        fun comprobante(comprobante: Comprobante?) = apply { this.comprobante = comprobante }
        fun cliente(cliente: Cliente?) = apply { this.cliente = cliente }
        fun moneda(moneda: Moneda?) = apply { this.moneda = moneda }

        fun build() = ClienteMovimiento(
            clienteMovimientoId, comprobanteId, puntoVenta, numeroComprobante, fechaComprobante, clienteId,
            fechaVencimiento, negocioId, empresaId, importe, cancelado, neto, netoCancelado, montoIva, montoIvaRni,
            reintegroTurista, fechaContable, ordenContable, recibo, asignado, anulada, decreto104316, letraComprobante,
            montoExento, reservaId, montoCuentaCorriente, cierreCajaId, cierreRestaurantId, nivel, eliminar,
            cuentaCorriente, letras, cae, caeVencimiento, codigoBarras, participacion, monedaId, cotizacion,
            observaciones,
            trackUuid,
            clienteMovimientoIdSlave,
            comprobante,
            cliente,
            moneda
        )
    }
}
