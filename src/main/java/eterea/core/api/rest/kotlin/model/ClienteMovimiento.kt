package eterea.core.api.rest.kotlin.model

import com.fasterxml.jackson.annotation.JsonFormat
import eterea.core.api.rest.model.Comprobante
import jakarta.persistence.*
import java.math.BigDecimal
import java.time.OffsetDateTime

@Entity
@Table(name = "movclie")
data class ClienteMovimiento(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "clave")
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
    var ordenContable: Int? = null,

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
    var cierreCajaId: Long? = null,

    @Column(name = "mcl_cir_id")
    var cierreRestaurantId: Long? = null,

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

    @Column(name = "clavev")
    var clienteMovimientoIdSlave: Long? = null,

    @OneToOne(optional = true)
    @JoinColumn(name = "cgocomprob", insertable = false, updatable = false)
    var comprobante: Comprobante? = null,

    @OneToOne(optional = true)
    @JoinColumn(name = "cgoclie", insertable = false, updatable = false)
    var cliente: Cliente? = null

) : Auditable()
