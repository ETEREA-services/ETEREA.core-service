package eterea.core.service.kotlin.model

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.json.JsonMapper
import jakarta.persistence.*
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import java.math.BigDecimal
import java.time.OffsetDateTime

@Entity
@Table(name = "movcon", uniqueConstraints = [UniqueConstraint(columnNames = ["fecha", "nrocomp", "item"])])
data class CuentaMovimiento(

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var cuentaMovimientoId: Long? = null,

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
    var fecha: OffsetDateTime? = null,

    @Column(name = "nrocomp")
    var orden: Int? = null,

    var item: Int? = null,
    var debita: Byte = 0,

    @Column(name = "mco_neg_id")
    var negocioId: Int? = null,

    @Column(name = "cuenta")
    var numeroCuenta: Long? = null,

    @Column(name = "cgotcomp")
    var comprobanteId: Int? = null,

    var concepto: String? = null,
    var importe: BigDecimal = BigDecimal.ZERO,

    @Column(name = "cgosub")
    var subrubroId: Long? = null,

    @Column(name = "cgoprov")
    var proveedorId: Long = 0,

    @Column(name = "cgoclie")
    var clienteId: Long? = null,

    @Column(name = "mco_cic_id")
    var cierreCajaId: Long = 0,

    @Column(name = "mco_nivel")
    var nivel: Int = 0,

    @Column(name = "mco_mcf_firma")
    var firma: Long = 0,

    @Column(name = "mco_tas_id")
    var tipoAsientoId: Int = 0,

    @Column(name = "articulomovimiento_id")
    var articuloMovimientoId: Long = 0,

    var ejercicioId: Int? = null,
    var inflacion: Byte = 0,
    var trackUuid: String? = null,

    @OneToOne(optional = true)
    @JoinColumn(name = "cuenta", insertable = false, updatable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    var cuenta: Cuenta? = null,

    @OneToOne(optional = true)
    @JoinColumn(name = "cgotcomp", insertable = false, updatable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    var comprobante: Comprobante? = null,

    @OneToOne(optional = true)
    @JoinColumn(name = "mco_neg_id", insertable = false, updatable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    var negocio: Negocio? = null

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

    data class Builder(
        var cuentaMovimientoId: Long? = null,
        var fecha: OffsetDateTime? = null,
        var orden: Int? = null,
        var item: Int? = null,
        var debita: Byte = 0,
        var negocioId: Int? = null,
        var numeroCuenta: Long? = null,
        var comprobanteId: Int? = null,
        var concepto: String? = null,
        var importe: BigDecimal = BigDecimal.ZERO,
        var subrubroId: Long? = null,
        var proveedorId: Long = 0,
        var clienteId: Long? = null,
        var cierreCajaId: Long = 0,
        var nivel: Int = 0,
        var firma: Long = 0,
        var tipoAsientoId: Int = 0,
        var articuloMovimientoId: Long = 0,
        var ejercicioId: Int? = null,
        var inflacion: Byte = 0,
        var trackUuid: String? = null,
        var cuenta: Cuenta? = null,
        var comprobante: Comprobante? = null,
        var negocio: Negocio? = null
    ) {
        fun cuentaMovimientoId(cuentaMovimientoId: Long?) = apply { this.cuentaMovimientoId = cuentaMovimientoId }
        fun fecha(fecha: OffsetDateTime?) = apply { this.fecha = fecha }
        fun orden(orden: Int?) = apply { this.orden = orden }
        fun item(item: Int?) = apply { this.item = item }
        fun debita(debita: Byte) = apply { this.debita = debita }
        fun negocioId(negocioId: Int?) = apply { this.negocioId = negocioId }
        fun numeroCuenta(numeroCuenta: Long?) = apply { this.numeroCuenta = numeroCuenta }
        fun comprobanteId(comprobanteId: Int?) = apply { this.comprobanteId = comprobanteId }
        fun concepto(concepto: String?) = apply { this.concepto = concepto }
        fun importe(importe: BigDecimal) = apply { this.importe = importe }
        fun subrubroId(subrubroId: Long?) = apply { this.subrubroId = subrubroId }
        fun proveedorId(proveedorId: Long) = apply { this.proveedorId = proveedorId }
        fun clienteId(clienteId: Long?) = apply { this.clienteId = clienteId }
        fun cierreCajaId(cierreCajaId: Long) = apply { this.cierreCajaId = cierreCajaId }
        fun nivel(nivel: Int) = apply { this.nivel = nivel }
        fun firma(firma: Long) = apply { this.firma = firma }
        fun tipoAsientoId(tipoAsientoId: Int) = apply { this.tipoAsientoId = tipoAsientoId }
        fun articuloMovimientoId(articuloMovimientoId: Long) =
            apply { this.articuloMovimientoId = articuloMovimientoId }

        fun ejercicioId(ejercicioId: Int?) = apply { this.ejercicioId = ejercicioId }
        fun inflacion(inflacion: Byte) = apply { this.inflacion = inflacion }
        fun trackUuid(trackUuid: String?) = apply { this.trackUuid = trackUuid }
        fun cuenta(cuenta: Cuenta?) = apply { this.cuenta = cuenta }
        fun comprobante(comprobante: Comprobante?) = apply { this.comprobante = comprobante }
        fun negocio(negocio: Negocio?) = apply { this.negocio = negocio }

        fun build() = CuentaMovimiento(
            cuentaMovimientoId, fecha, orden, item, debita, negocioId, numeroCuenta,
            comprobanteId, concepto, importe, subrubroId, proveedorId, clienteId,
            cierreCajaId, nivel, firma, tipoAsientoId, articuloMovimientoId,
            ejercicioId,
            inflacion,
            trackUuid,
            cuenta,
            comprobante,
            negocio
        )
    }
}
