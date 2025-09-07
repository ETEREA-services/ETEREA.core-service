package eterea.core.service.kotlin.model

import com.fasterxml.jackson.annotation.JsonFormat
import eterea.core.service.tool.Jsonifier
import jakarta.persistence.Entity
import jakarta.persistence.Table
import jakarta.persistence.Column
import jakarta.persistence.Id
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import java.time.OffsetDateTime

import java.math.BigDecimal

@Entity
@Table(name = "movstock")
data class StockMovimiento(

    @Id
    @Column(name = "clave")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var stockMovimientoId: Long? = null,

    @Column(name = "cgocomprob")
    var comprobanteId: Int? = null,

    @Column(name = "nrocompinterno")
    var numeroComprobanteInterno: Long = 0,

    @Column(name = "mst_neg_id")
    var negocioId: Int? = null,

    @Column(name = "mst_neg_id_desde")
    var negocioIdDesde: Int? = null,

    @Column(name = "mst_cgocentrodesde")
    var centroStockIdDesde: Int? = null,

    @Column(name = "mst_neg_id_hasta")
    var negocioIdHasta: Int? = null,

    @Column(name = "mst_cgocentrohasta")
    var centroStockIdHasta: Int? = null,

    @Column(name = "mst_cstidhnombre")
    var centroStockIdHastaNombre: String = "",

    @Column(name = "fechareg")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
    var fechaRegistro: OffsetDateTime? = null,

    @Column(name = "cgoprov")
    var proveedorId: Long? = null,

    @Column(name = "cgocli")
    var clienteId: Long? = null,

    var legajo: Long? = null,

    @Column(name = "fechacomprob")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
    var fechaComprobante: OffsetDateTime? = null,

    @Column(name = "cgocomprob2")
    var comprobanteIdFactura: Int? = null,

    @Column(name = "prefijo")
    var prefijoFactura: Int? = null,

    @Column(name = "nrocomprob")
    var numeroComprobanteFactura: Long? = null,

    var importe: BigDecimal = BigDecimal.ZERO,

    @Column(name = "letracomanda")
    var letraComanda: String? = null,

    @Column(name = "mst_observ")
    var observaciones: String? = null,

    @Column(name = "mst_cic_id")
    var cierreCajaId: Long? = null,

    @Column(name = "mst_cir_id")
    var cierreRestaurantId: Long? = null,

    @Column(name = "mst_nivel")
    var nivel: Int = 0,

    @Column(name = "mst_fechacontable")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
    var fechaContable: OffsetDateTime? = null,

    @Column(name = "mst_nrocompcontable")
    var ordenContable: Int? = null,

    @Column(name = "mst_neg_id_otro")
    var negocioIdOtro: Int? = null,

    @Column(name = "mst_genauto")
    var generacionAutomatica: Byte = 0,

    @Column(name = "mst_pendiente")
    var pendiente: Byte = 0,

    @Column(name = "mst_rechazada")
    var rechazada: Byte = 0,

    var facturaProveedor: Byte = 0,
    var netoFactura: BigDecimal = BigDecimal.ZERO,
    var netoRegistrado: BigDecimal = BigDecimal.ZERO

) : Auditable() {

    fun jsonify(): String {
        return Jsonifier.builder(this).build();
    }

}
