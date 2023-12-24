package eterea.core.api.rest.kotlin.model

import com.fasterxml.jackson.annotation.JsonFormat
import eterea.core.api.rest.model.Hotel
import eterea.core.api.rest.model.Proveedor
import eterea.core.api.rest.model.Usuario
import jakarta.persistence.*
import java.sql.Time
import java.time.OffsetDateTime

@Entity
@Table
data class Voucher(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vou_id")
    var voucherId: Long? = null,

    @Column(name = "vou_fechareserva")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
    var fechaToma: OffsetDateTime? = null,

    @Column(name = "vou_fechain")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
    var fechaServicio: OffsetDateTime? = null,

    @Column(name = "vou_vto")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
    var fechaVencimiento: OffsetDateTime? = null,

    @Column(name = "vou_horavto")
    var horaVencimiento: Time? = null,

    @Column(name = "vou_nombrepax")
    var nombrePax: String? = null,

    @Column(name = "vou_paxs")
    var paxs: Int? = null,

    @Column(name = "vou_subeen")
    var subeEn: String? = null,

    @Column(name = "vou_productos")
    var productos: String? = null,

    @Column(name = "vou_voucher")
    var tieneVoucher: Byte? = null,

    @Column(name = "vou_cli_id")
    var clienteId: Long? = null,

    @Column(name = "vou_observ")
    var observaciones: String? = null,

    @Column(name = "vou_confirmado")
    var confirmado: Byte? = null,

    var pagaCacheuta: Byte? = null,

    @Column(name = "vou_hot_id")
    var hotelId: Int? = null,

    @Column(name = "vou_cliente")
    var contacto: String? = null,

    @Column(name = "vou_paxsreales")
    var paxsReales: Int? = null,

    @Column(name = "vou_pro_id")
    var proveedorId: Int? = null,

    @Column(name = "vou_planilla")
    var planilla: String? = null,

    @Column(name = "vou_res_id")
    var reservaId: Long? = null,

    @Column(name = "vou_nrovoucher")
    var numeroVoucher: String? = null,

    @Column(name = "vou_usuario")
    var usuario: String? = null,

    @Column(name = "vou_fecharecepcion")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
    var fechaRecepcion: OffsetDateTime? = null,

    @Column(name = "vou_fechaemision")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
    var fechaEmision: OffsetDateTime? = null,

    @Column(name = "vou_numero")
    var numero: String? = null,

    @Column(name = "vou_cantidadpax")
    var cantidadPax: Int? = null,

    @Column(name = "vou_nombre")
    var nombre: String? = null,

    @Column(name = "vou_contraslado")
    var conTraslado: Byte? = null,

    @Column(name = "vou_paxsnoshow")
    var paxsNoShow: Int? = null,

    @Column(name = "vou_reo_id")
    var reservaOrigenId: Int? = null,

    var fechaAbierta: Byte = 0,
    var ventaInternet: Byte = 0,

    @OneToOne(optional = true)
    @JoinColumn(name = "vou_usuario", insertable = false, updatable = false)
    var user: Usuario? = null,

    @OneToOne(optional = true)
    @JoinColumn(name = "vou_cli_id", insertable = false, updatable = false)
    var cliente: Cliente? = null,

    @OneToOne(optional = true)
    @JoinColumn(name = "vou_hot_id", insertable = false, updatable = false)
    var hotel: Hotel? = null,

    @OneToOne(optional = true)
    @JoinColumn(name = "vou_pro_id", insertable = false, updatable = false)
    var proveedor: Proveedor? = null,

    @OneToOne(optional = true)
    @JoinColumn(name = "vou_res_id", insertable = false, updatable = false)
    var reserva: Reserva? = null

) : Auditable()
