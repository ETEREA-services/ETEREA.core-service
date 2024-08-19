package eterea.core.api.rest.kotlin.model

import com.fasterxml.jackson.annotation.JsonFormat
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
    var horaVencimiento: Time? = Time.valueOf("00:00:00"),

    @Column(name = "vou_nombrepax")
    var nombrePax: String? = null,

    @Column(name = "vou_paxs")
    var paxs: Int? = null,

    @Column(name = "vou_subeen")
    var subeEn: String = "",

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
    var contacto: String = "",

    @Column(name = "vou_paxsreales")
    var paxsReales: Int? = null,

    @Column(name = "vou_pro_id")
    var proveedorId: Int? = null,

    @Column(name = "vou_planilla")
    var planilla: String = "",

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

) {
    data class Builder(
        var voucherId: Long? = null,
        var fechaToma: OffsetDateTime? = null,
        var fechaServicio: OffsetDateTime? = null,
        var fechaVencimiento: OffsetDateTime? = null,
        var horaVencimiento: Time? = Time.valueOf("00:00:00"),
        var nombrePax: String? = null,
        var paxs: Int? = null,
        var subeEn: String = "",
        var productos: String? = null,
        var tieneVoucher: Byte? = null,
        var clienteId: Long? = null,
        var observaciones: String? = null,
        var confirmado: Byte? = null,
        var pagaCacheuta: Byte? = null,
        var hotelId: Int? = null,
        var contacto: String = "",
        var paxsReales: Int? = null,
        var proveedorId: Int? = null,
        var planilla: String = "",
        var reservaId: Long? = null,
        var numeroVoucher: String? = null,
        var usuario: String? = null,
        var fechaRecepcion: OffsetDateTime? = null,
        var fechaEmision: OffsetDateTime? = null,
        var numero: String? = null,
        var cantidadPax: Int? = null,
        var nombre: String? = null,
        var conTraslado: Byte? = null,
        var paxsNoShow: Int? = null,
        var reservaOrigenId: Int? = null,
        var fechaAbierta: Byte = 0,
        var ventaInternet: Byte = 0,
        var user: Usuario? = null,
        var cliente: Cliente? = null,
        var hotel: Hotel? = null,
        var proveedor: Proveedor? = null,
        var reserva: Reserva? = null
    ) {
        fun voucherId(voucherId: Long?) = apply { this.voucherId = voucherId }
        fun fechaToma(fechaToma: OffsetDateTime?) = apply { this.fechaToma = fechaToma }
        fun fechaServicio(fechaServicio: OffsetDateTime?) = apply { this.fechaServicio = fechaServicio }
        fun fechaVencimiento(fechaVencimiento: OffsetDateTime?) = apply { this.fechaVencimiento = fechaVencimiento }
        fun horaVencimiento(horaVencimiento: Time?) = apply { this.horaVencimiento = horaVencimiento }
        fun nombrePax(nombrePax: String?) = apply { this.nombrePax = nombrePax }
        fun paxs(paxs: Int?) = apply { this.paxs = paxs }
        fun subeEn(subeEn: String) = apply { this.subeEn = subeEn }
        fun productos(productos: String?) = apply { this.productos = productos }
        fun tieneVoucher(tieneVoucher: Byte?) = apply { this.tieneVoucher = tieneVoucher }
        fun clienteId(clienteId: Long?) = apply { this.clienteId = clienteId }
        fun observaciones(observaciones: String?) = apply { this.observaciones = observaciones }
        fun confirmado(confirmado: Byte?) = apply { this.confirmado = confirmado }
        fun pagaCacheuta(pagaCacheuta: Byte?) = apply { this.pagaCacheuta = pagaCacheuta }
        fun hotelId(hotelId: Int?) = apply { this.hotelId = hotelId }
        fun contacto(contacto: String) = apply { this.contacto = contacto }
        fun paxsReales(paxsReales: Int?) = apply { this.paxsReales = paxsReales }
        fun proveedorId(proveedorId: Int?) = apply { this.proveedorId = proveedorId }
        fun planilla(planilla: String) = apply { this.planilla = planilla }
        fun reservaId(reservaId: Long?) = apply { this.reservaId = reservaId }
        fun numeroVoucher(numeroVoucher: String?) = apply { this.numeroVoucher = numeroVoucher }
        fun usuario(usuario: String?) = apply { this.usuario = usuario }
        fun fechaRecepcion(fechaRecepcion: OffsetDateTime?) = apply { this.fechaRecepcion = fechaRecepcion }
        fun fechaEmision(fechaEmision: OffsetDateTime?) = apply { this.fechaEmision = fechaEmision }
        fun numero(numero: String?) = apply { this.numero = numero }
        fun cantidadPax(cantidadPax: Int?) = apply { this.cantidadPax = cantidadPax }
        fun nombre(nombre: String?) = apply { this.nombre = nombre }
        fun conTraslado(conTraslado: Byte?) = apply { this.conTraslado = conTraslado }
        fun paxsNoShow(paxsNoShow: Int?) = apply { this.paxsNoShow = paxsNoShow }
        fun reservaOrigenId(reservaOrigenId: Int?) = apply { this.reservaOrigenId = reservaOrigenId }
        fun fechaAbierta(fechaAbierta: Byte) = apply { this.fechaAbierta = fechaAbierta }
        fun ventaInternet(ventaInternet: Byte) = apply { this.ventaInternet = ventaInternet }
        fun user(user: Usuario?) = apply { this.user = user }
        fun cliente(cliente: Cliente?) = apply { this.cliente = cliente }
        fun hotel(hotel: Hotel?) = apply { this.hotel = hotel }
        fun proveedor(proveedor: Proveedor?) = apply { this.proveedor = proveedor }
        fun reserva(reserva: Reserva?) = apply { this.reserva = reserva }

        fun build() = Voucher(
            voucherId, fechaToma, fechaServicio, fechaVencimiento, horaVencimiento, nombrePax, paxs, subeEn,
            productos, tieneVoucher, clienteId, observaciones, confirmado, pagaCacheuta, hotelId, contacto,
            paxsReales, proveedorId, planilla, reservaId, numeroVoucher, usuario, fechaRecepcion, fechaEmision,
            numero, cantidadPax, nombre, conTraslado, paxsNoShow, reservaOrigenId, fechaAbierta, ventaInternet,
            user, cliente, hotel, proveedor, reserva
        )
    }
}

