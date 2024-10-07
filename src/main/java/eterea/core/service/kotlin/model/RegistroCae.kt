package eterea.core.service.kotlin.model

import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(
    name = "registrocae",
    uniqueConstraints = [UniqueConstraint(columnNames = ["rec_tco_id", "rec_prefijo", "rec_nrocomprob"])]
)
data class RegistroCae(

    @Id
    @Column(name = "rec_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var registroCaeId: Long? = null,

    @Column(name = "rec_tco_id")
    var comprobanteId: Int? = null,

    @Column(name = "rec_prefijo")
    var puntoVenta: Int? = null,

    @Column(name = "rec_nrocomprob")
    var numeroComprobante: Long? = null,

    @Column(name = "rec_cli_id")
    var clienteId: Long? = null,

    @Column(name = "rec_cuit")
    var cuit: String? = null,

    @Column(name = "rec_total")
    var total: BigDecimal = BigDecimal.ZERO,

    @Column(name = "rec_exento")
    var exento: BigDecimal = BigDecimal.ZERO,

    @Column(name = "rec_neto")
    var neto: BigDecimal = BigDecimal.ZERO,

    @Column(name = "rec_neto105")
    var neto105: BigDecimal = BigDecimal.ZERO,

    @Column(name = "rec_iva")
    var iva: BigDecimal = BigDecimal.ZERO,

    @Column(name = "rec_iva105")
    var iva105: BigDecimal = BigDecimal.ZERO,

    @Column(name = "rec_cae")
    var cae: String? = null,

    @Column(name = "rec_fecha")
    var fecha: String? = null,

    @Column(name = "rec_caevenc")
    var caeVencimiento: String? = null,

    @Column(name = "rec_barras")
    var barras: String = "",

    @Column(name = "tipo_documento")
    var tipoDocumento: Int? = null,

    @Column(name = "numero_documento")
    var numeroDocumento: BigDecimal = BigDecimal.ZERO,

    @Column(name = "cliente_movimiento_id_asociado")
    var clienteMovimientoIdAsociado: Long? = null

) : Auditable() {

    class Builder {
        var registroCaeId: Long? = null
        var comprobanteId: Int? = null
        var puntoVenta: Int? = null
        var numeroComprobante: Long? = null
        var clienteId: Long? = null
        var cuit: String? = null
        var total: BigDecimal = BigDecimal.ZERO
        var exento: BigDecimal = BigDecimal.ZERO
        var neto: BigDecimal = BigDecimal.ZERO
        var neto105: BigDecimal = BigDecimal.ZERO
        var iva: BigDecimal = BigDecimal.ZERO
        var iva105: BigDecimal = BigDecimal.ZERO
        var cae: String? = null
        var fecha: String? = null
        var caeVencimiento: String? = null
        var barras: String = ""
        var tipoDocumento: Int? = null
        var numeroDocumento: BigDecimal = BigDecimal.ZERO
        var clienteMovimientoIdAsociado: Long? = null

        fun registroCaeId(registroCaeId: Long?) = apply { this.registroCaeId = registroCaeId }
        fun comprobanteId(comprobanteId: Int?) = apply { this.comprobanteId = comprobanteId }
        fun puntoVenta(puntoVenta: Int?) = apply { this.puntoVenta = puntoVenta }
        fun numeroComprobante(numeroComprobante: Long?) = apply { this.numeroComprobante = numeroComprobante }
        fun clienteId(clienteId: Long?) = apply { this.clienteId = clienteId }
        fun cuit(cuit: String?) = apply { this.cuit = cuit }
        fun total(total: BigDecimal) = apply { this.total = total }
        fun exento(exento: BigDecimal) = apply { this.exento = exento }
        fun neto(neto: BigDecimal) = apply { this.neto = neto }
        fun neto105(neto105: BigDecimal) = apply { this.neto105 = neto105 }
        fun iva(iva: BigDecimal) = apply { this.iva = iva }
        fun iva105(iva105: BigDecimal) = apply { this.iva105 = iva105 }
        fun cae(cae: String?) = apply { this.cae = cae }
        fun fecha(fecha: String?) = apply { this.fecha = fecha }
        fun caeVencimiento(caeVencimiento: String?) = apply { this.caeVencimiento = caeVencimiento }
        fun barras(barras: String) = apply { this.barras = barras }
        fun tipoDocumento(tipoDocumento: Int?) = apply { this.tipoDocumento = tipoDocumento }
        fun numeroDocumento(numeroDocumento: BigDecimal) = apply { this.numeroDocumento = numeroDocumento }
        fun clienteMovimientoIdAsociado(clienteMovimientoIdAsociado: Long?) =
            apply { this.clienteMovimientoIdAsociado = clienteMovimientoIdAsociado }

        fun build() = RegistroCae(
            registroCaeId, comprobanteId, puntoVenta, numeroComprobante, clienteId, cuit, total, exento, neto, neto105,
            iva, iva105, cae, fecha, caeVencimiento, barras, tipoDocumento, numeroDocumento, clienteMovimientoIdAsociado
        )
    }
}
