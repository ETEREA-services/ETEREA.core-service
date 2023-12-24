package eterea.core.api.rest.kotlin.model

import com.fasterxml.jackson.annotation.JsonFormat
import jakarta.persistence.*
import java.time.OffsetDateTime
import java.util.*

@Entity
@Table
data class Reserva(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "res_id")
    var reservaId: Long? = null,

    @jakarta.persistence.Column(name = "res_neg_id")
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
    var cliente: Cliente? = null

) : Auditable()
