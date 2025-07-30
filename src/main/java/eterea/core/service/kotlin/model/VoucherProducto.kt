package eterea.core.service.kotlin.model

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.json.JsonMapper
import jakarta.persistence.*

@Entity
@Table(name = "voucherproducto")
data class VoucherProducto(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vpr_id")
    var voucherProductoId: Long? = null,

    @Column(name = "vpr_vou_id")
    var voucherId: Long? = null,

    @Column(name = "vpr_prd_id")
    var productoId: Int? = null,

    @Column(name = "vpr_paxs")
    var cantidadPaxs: Int? = null,

    var trackUuid: String? = null,

    @OneToOne(optional = true)
    @JoinColumn(name = "vpr_prd_id", insertable = false, updatable = false)
    var producto: Producto? = null

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
            return "jsonify error ${e.message}"
        }
    }

    data class Builder(
        var voucherProductoId: Long? = null,
        var voucherId: Long? = null,
        var productoId: Int? = null,
        var cantidadPaxs: Int? = null,
        var trackUuid: String? = null,
        var producto: Producto? = null
    ) {
        fun voucherProductoId(voucherProductoId: Long?) = apply { this.voucherProductoId = voucherProductoId }
        fun voucherId(voucherId: Long?) = apply { this.voucherId = voucherId }
        fun productoId(productoId: Int?) = apply { this.productoId = productoId }
        fun cantidadPaxs(cantidadPaxs: Int?) = apply { this.cantidadPaxs = cantidadPaxs }
        fun trackUuid(trackUuid: String?) = apply { this.trackUuid = trackUuid }
        fun producto(producto: Producto?) = apply {this.producto = producto}
        fun build() = VoucherProducto(
            voucherProductoId,
            voucherId,
            productoId,
            cantidadPaxs,
            trackUuid,
            producto
        )
    }
}
