package eterea.core.service.kotlin.model

import com.fasterxml.jackson.annotation.JsonFormat
import jakarta.persistence.*
import java.math.BigDecimal
import java.time.OffsetDateTime

@Entity
@Table(name = "valores")
data class ValorMovimiento(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "clave")
    var valorMovimientoId: Long? = null,

    @Column(name = "val_neg_id")
    var negocioId: Int? = null,

    @Column(name = "codigo")
    var valorId: Int? = null,

    @Column(name = "cgoprov")
    var proveedorId: Long? = null,

    @Column(name = "cgocli")
    var clienteId: Long? = null,

    @Column(name = "fechaemi")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
    var fechaEmision: OffsetDateTime? = null,

    @Column(name = "fechavto")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
    var fechaVencimiento: OffsetDateTime? = null,

    @Column(name = "val_tco_id")
    var comprobanteId: Int? = null,

    @Column(name = "nrocomprob")
    var numeroComprobante: Long? = null,

    var importe: BigDecimal = BigDecimal.ZERO,

    @Column(name = "cgocontable")
    var numeroCuenta: Long? = null,

    @Column(name = "fechareg")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
    var fechaContable: OffsetDateTime? = null,

    @Column(name = "nrocompcontable")
    var ordenContable: Int = 0,

    @Column(name = "clavemovp")
    var proveedorMovimientoId: Long? = null,

    @Column(name = "clavemovv")
    var clienteMovimientoId: Long? = null,

    var titular: String? = null,
    var banco: String? = null,
    var receptor: String? = null,

    @Column(name = "cgoestado")
    var estadoId: Int? = null,

    @Column(name = "fechaentrega")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
    var fechaEntrega: OffsetDateTime? = null,

    var tanda: Long = 0,

    @Column(name = "tandaindex")
    var tandaIndex: Long = 0,

    @Column(name = "val_cic_id")
    var cierreCajaId: Long? = null,

    @Column(name = "val_nivel")
    var nivel: Int = 0,

    var observaciones: String? = null,

    @Column(name = "clavev")
    var valorMovimientoIdSlave: Long = 0,

    @OneToOne(optional = true)
    @JoinColumn(name = "codigo", insertable = false, updatable = false)
    var valor: Valor? = null

) : Auditable() {
    data class Builder(
        var valorMovimientoId: Long? = null,
        var negocioId: Int? = null,
        var valorId: Int? = null,
        var proveedorId: Long? = null,
        var clienteId: Long? = null,
        var fechaEmision: OffsetDateTime? = null,
        var fechaVencimiento: OffsetDateTime? = null,
        var comprobanteId: Int? = null,
        var numeroComprobante: Long? = null,
        var importe: BigDecimal = BigDecimal.ZERO,
        var numeroCuenta: Long? = null,
        var fechaContable: OffsetDateTime? = null,
        var ordenContable: Int = 0,
        var proveedorMovimientoId: Long? = null,
        var clienteMovimientoId: Long? = null,
        var titular: String? = null,
        var banco: String? = null,
        var receptor: String? = null,
        var estadoId: Int? = null,
        var fechaEntrega: OffsetDateTime? = null,
        var tanda: Long = 0,
        var tandaIndex: Long = 0,
        var cierreCajaId: Long? = null,
        var nivel: Int = 0,
        var observaciones: String? = null,
        var valorMovimientoIdSlave: Long = 0,
        var valor: Valor? = null
    ) {
        fun valorMovimientoId(valorMovimientoId: Long?) = apply { this.valorMovimientoId = valorMovimientoId }
        fun negocioId(negocioId: Int?) = apply { this.negocioId = negocioId }
        fun valorId(valorId: Int?) = apply { this.valorId = valorId }
        fun proveedorId(proveedorId: Long?) = apply { this.proveedorId = proveedorId }
        fun clienteId(clienteId: Long?) = apply { this.clienteId = clienteId }
        fun fechaEmision(fechaEmision: OffsetDateTime?) = apply { this.fechaEmision = fechaEmision }
        fun fechaVencimiento(fechaVencimiento: OffsetDateTime?) = apply { this.fechaVencimiento = fechaVencimiento }
        fun comprobanteId(comprobanteId: Int?) = apply { this.comprobanteId = comprobanteId }
        fun numeroComprobante(numeroComprobante: Long?) = apply { this.numeroComprobante = numeroComprobante }
        fun importe(importe: BigDecimal) = apply { this.importe = importe }
        fun numeroCuenta(numeroCuenta: Long?) = apply { this.numeroCuenta = numeroCuenta }
        fun fechaContable(fechaContable: OffsetDateTime?) = apply { this.fechaContable = fechaContable }
        fun ordenContable(ordenContable: Int) = apply { this.ordenContable = ordenContable }
        fun proveedorMovimientoId(proveedorMovimientoId: Long?) =
            apply { this.proveedorMovimientoId = proveedorMovimientoId }

        fun clienteMovimientoId(clienteMovimientoId: Long?) = apply { this.clienteMovimientoId = clienteMovimientoId }
        fun titular(titular: String?) = apply { this.titular = titular }
        fun banco(banco: String?) = apply { this.banco = banco }
        fun receptor(receptor: String?) = apply { this.receptor = receptor }
        fun estadoId(estadoId: Int?) = apply { this.estadoId = estadoId }
        fun fechaEntrega(fechaEntrega: OffsetDateTime?) = apply { this.fechaEntrega = fechaEntrega }
        fun tanda(tanda: Long) = apply { this.tanda = tanda }
        fun tandaIndex(tandaIndex: Long) = apply { this.tandaIndex = tandaIndex }
        fun cierreCajaId(cierreCajaId: Long?) = apply { this.cierreCajaId = cierreCajaId }
        fun nivel(nivel: Int) = apply { this.nivel = nivel }
        fun observaciones(observaciones: String?) = apply { this.observaciones = observaciones }
        fun valorMovimientoIdSlave(valorMovimientoIdSlave: Long) =
            apply { this.valorMovimientoIdSlave = valorMovimientoIdSlave }

        fun valor(valor: Valor?) = apply { this.valor = valor }

        fun build() = ValorMovimiento(
            valorMovimientoId, negocioId, valorId, proveedorId, clienteId,
            fechaEmision, fechaVencimiento, comprobanteId, numeroComprobante,
            importe, numeroCuenta, fechaContable, ordenContable, proveedorMovimientoId,
            clienteMovimientoId, titular, banco, receptor, estadoId,
            fechaEntrega, tanda, tandaIndex, cierreCajaId, nivel, observaciones, valorMovimientoIdSlave, valor
        )
    }
}
