package eterea.core.service.kotlin.model

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.json.JsonMapper
import eterea.core.service.model.Auditable
import eterea.core.service.tool.Jsonifier
import jakarta.persistence.*

@Entity
@Table(name = "tiposcomprob")
data class Comprobante(

    @Id
    @Column(name = "codigo")
    var comprobanteId: Int? = null,
    
    var descripcion: String = "",

    @Column(name = "tco_neg_id")
    var negocioId: Int? = null,

    var modulo: Int? = null,
    var stock: Byte = 0,
    var rendicion: Byte = 0,

    @Column(name = "opago")
    var ordenPago: Byte = 0,

    @Column(name = "aplicapend")
    var aplicaPendiente: Byte = 0,

    @Column(name = "ctacte")
    var cuentaCorriente: Byte = 0,

    var debita: Byte = 0,
    var iva: Byte = 0,
    var ganancias: Byte = 0,
    var aplicable: Byte = 0,

    @Column(name = "libroiva")
    var libroIva: Byte = 0,

    @Column(name = "tipocomprob")
    var letraComprobante: String? = null,

    var recibo: Byte = 0,
    var contado: Byte = 0,
    var transferencia: Byte = 0,
    var imprime: Byte = 0,

    @Column(name = "cgoanulacion")
    var comprobanteIdAnulacion: Int? = null,

    @Column(name = "cgocentroentrega")
    var centroStockIdEntrega: Int? = null,

    @Column(name = "cgocentrorecibe")
    var centroStockIdRecibe: Int? = null,

    @Column(name = "diasvigencia")
    var diasVigencia: Int? = null,

    var concepto: Byte = 0,
    var personal: Byte = 0,
    var comanda: Byte = 0,

    @Column(name = "tco_puntovta")
    var puntoVenta: Int = 0,

    @Column(name = "cgomozo")
    var codigoMozo: Byte = 0,

    @Column(name = "tco_transferir")
    var transferir: Byte = 0,

    @Column(name = "tco_cgocontable")
    var numeroCuenta: Long? = null,

    @Column(name = "tco_nivel")
    var nivel: Int = 0,

    @Column(name = "tco_fiscalizadora")
    var fiscalizadora: Byte = 0,

    @Column(name = "tco_invisible")
    var invisible: Byte = 0,

    @Column(name = "tco_tipoafip")
    var comprobanteAfipId: Int? = null,

    @Column(name = "tco_factelect")
    var facturaElectronica: Byte = 0,

    var asociado: Byte = 0,

    @OneToOne(optional = true)
    @JoinColumn(name = "tco_cgocontable", insertable = false, updatable = false)
    var cuenta: Cuenta? = null,

    @OneToOne(optional = true)
    @JoinColumn(name = "tco_tipoafip", insertable = false, updatable = false)
    var comprobanteAfip: ComprobanteAfip? = null

) : Auditable() {

    fun jsonify(): String {
        return Jsonifier.builder(this).build()
    }

}
