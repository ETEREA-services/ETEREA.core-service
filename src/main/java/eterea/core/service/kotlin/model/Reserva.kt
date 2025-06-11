package eterea.core.service.kotlin.model

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import java.math.BigDecimal
import java.time.OffsetDateTime
import java.util.*

@Entity
data class Reserva(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "res_id")
    var reservaId: Long? = null,

    @Column(name = "res_neg_id")
    var negocioId: Int? = null,

    @Column(name = "res_cli_id")
    var clienteId: Long? = null,

    @Column(name = "res_fecha")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
    var fechaToma: OffsetDateTime? = null,

    @Column(name = "res_in")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
    var fechaInServicio: OffsetDateTime? = null,

    @Column(name = "res_out")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
    var fechaOutServicio: OffsetDateTime? = null,

    @Column(name = "res_vto")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
    var fechaVencimiento: OffsetDateTime? = null,

    @Column(name = "res_horavto")
    @Temporal(TemporalType.TIME)
    var horaVencimiento: Date? = null,

    @Column(name = "res_avisomail")
    var avisoMail: Byte = 0,

    @Column(name = "res_pendiente")
    var pendiente: Byte = 0,

    @Column(name = "res_confirmada")
    var confirmada: Byte = 0,

    @Column(name = "res_facturada")
    var facturada: Byte = 0,

    @Column(name = "res_anulada")
    var anulada: Byte = 0,

    @Column(name = "res_eliminada")
    var eliminada: Byte = 0,

    var verificada: Byte = 0,

    @Column(name = "res_nombrepax")
    var nombrePax: String = "",

    @Column(name = "res_paxs")
    var cantidadPaxs: Int = 0,

    @Column(name = "res_observaciones")
    var observaciones: String = "",

    @Column(name = "res_vou_id")
    var voucherId: Long? = null,

    @Column(name = "res_pagacomision")
    var pagaComision: Byte = 0,

    @Column(name = "res_obscomision")
    var observacionesComision: String = "",

    @Column(name = "res_comisionpagada")
    var comisionPagada: Byte = 0,

    @Column(name = "res_pagacacheuta")
    var pagaCacheuta: Byte = 0,

    @Column(name = "res_facturadofuera")
    var facturadoFuera: Byte = 0,

    @Column(name = "res_reservaarticulo")
    var reservaArticulos: String = "",

    @Column(name = "res_usuario")
    var usuario: String = "",

    @Column(name = "res_cliente")
    var contacto: String = "",

    @Column(name = "res_reo_ID")
    var reservaOrigenId: Int? = null,

    @Column(name = "facturarextranjero")
    var facturarExtranjero: Byte = 0,

    @Column(name = "fecha_abierta")
    var fechaAbierta: Byte = 0,

    @OneToOne
    @JoinColumn(name = "res_cli_id", insertable = false, updatable = false)
    var cliente: Cliente? = null,

    // @OneToMany(mappedBy = "reserva")
    // @JsonIgnore()
    // var comentarios: List<ReservaComentario> = mutableListOf()

) : Auditable() {
    data class Builder(
        var reservaId: Long? = null,
        var negocioId: Int? = null,
        var clienteId: Long? = null,
        var fechaToma: OffsetDateTime? = null,
        var fechaInServicio: OffsetDateTime? = null,
        var fechaOutServicio: OffsetDateTime? = null,
        var fechaVencimiento: OffsetDateTime? = null,
        var horaVencimiento: Date? = null,
        var avisoMail: Byte = 0,
        var pendiente: Byte = 0,
        var confirmada: Byte = 0,
        var facturada: Byte = 0,
        var anulada: Byte = 0,
        var eliminada: Byte = 0,
        var verificada: Byte = 0,
        var nombrePax: String = "",
        var cantidadPaxs: Int = 0,
        var observaciones: String = "",
        var voucherId: Long? = null,
        var pagaComision: Byte = 0,
        var observacionesComision: String = "",
        var comisionPagada: Byte = 0,
        var pagaCacheuta: Byte = 0,
        var facturadoFuera: Byte = 0,
        var reservaArticulos: String = "",
        var usuario: String = "",
        var contacto: String = "",
        var reservaOrigenId: Int? = null,
        var facturarExtranjero: Byte = 0,
        var fechaAbierta: Byte = 0,
        var cliente: Cliente? = null,
        // var comentarios: List<ReservaComentario> = mutableListOf()
    ) {
        fun reservaId(reservaId: Long?) = apply { this.reservaId = reservaId }
        fun negocioId(negocioId: Int?) = apply { this.negocioId = negocioId }
        fun clienteId(clienteId: Long?) = apply { this.clienteId = clienteId }
        fun fechaToma(fechaToma: OffsetDateTime?) = apply { this.fechaToma = fechaToma }
        fun fechaInServicio(fechaInServicio: OffsetDateTime?) = apply { this.fechaInServicio = fechaInServicio }
        fun fechaOutServicio(fechaOutServicio: OffsetDateTime?) = apply { this.fechaOutServicio = fechaOutServicio }
        fun fechaVencimiento(fechaVencimiento: OffsetDateTime?) = apply { this.fechaVencimiento = fechaVencimiento }
        fun horaVencimiento(horaVencimiento: Date?) = apply { this.horaVencimiento = horaVencimiento }
        fun avisoMail(avisoMail: Byte) = apply { this.avisoMail = avisoMail }
        fun pendiente(pendiente: Byte) = apply { this.pendiente = pendiente }
        fun confirmada(confirmada: Byte) = apply { this.confirmada = confirmada }
        fun facturada(facturada: Byte) = apply { this.facturada = facturada }
        fun anulada(anulada: Byte) = apply { this.anulada = anulada }
        fun eliminada(eliminada: Byte) = apply { this.eliminada = eliminada }
        fun verificada(verificada: Byte) = apply { this.verificada = verificada }
        fun nombrePax(nombrePax: String) = apply { this.nombrePax = nombrePax }
        fun cantidadPaxs(cantidadPaxs: Int) = apply { this.cantidadPaxs = cantidadPaxs }
        fun observaciones(observaciones: String) = apply { this.observaciones = observaciones }
        fun voucherId(voucherId: Long?) = apply { this.voucherId = voucherId }
        fun pagaComision(pagaComision: Byte) = apply { this.pagaComision = pagaComision }
        fun observacionesComision(observacionesComision: String) =
            apply { this.observacionesComision = observacionesComision }

        fun comisionPagada(comisionPagada: Byte) = apply { this.comisionPagada = comisionPagada }
        fun pagaCacheuta(pagaCacheuta: Byte) = apply { this.pagaCacheuta = pagaCacheuta }
        fun facturadoFuera(facturadoFuera: Byte) = apply { this.facturadoFuera = facturadoFuera }
        fun reservaArticulos(reservaArticulos: String) = apply { this.reservaArticulos = reservaArticulos }
        fun usuario(usuario: String) = apply { this.usuario = usuario }
        fun contacto(contacto: String) = apply { this.contacto = contacto }
        fun reservaOrigenId(reservaOrigenId: Int?) = apply { this.reservaOrigenId = reservaOrigenId }
        fun facturarExtranjero(facturarExtranjero: Byte) = apply { this.facturarExtranjero = facturarExtranjero }
        fun fechaAbierta(fechaAbierta: Byte) = apply { this.fechaAbierta = fechaAbierta }
        fun cliente(cliente: Cliente?) = apply { this.cliente = cliente }
        // fun comentarios(comentarios: List<ReservaComentario>) = apply { this.comentarios = comentarios }

        fun build() = Reserva(
            reservaId,
            negocioId,
            clienteId,
            fechaToma,
            fechaInServicio,
            fechaOutServicio,
            fechaVencimiento,
            horaVencimiento,
            avisoMail,
            pendiente,
            confirmada,
            facturada,
            anulada,
            eliminada,
            verificada,
            nombrePax,
            cantidadPaxs,
            observaciones,
            voucherId,
            pagaComision,
            observacionesComision,
            comisionPagada,
            pagaCacheuta,
            facturadoFuera,
            reservaArticulos,
            usuario,
            contacto,
            reservaOrigenId,
            facturarExtranjero,
            fechaAbierta,
            cliente
            // comentarios
        )
    }
}
