package eterea.core.api.rest.kotlin.model

import jakarta.persistence.*

@Entity
@Table(name = "voucherproducto")
data class VoucherProducto(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "VPr_ID")
    var voucherProductoId: Long? = null,

    @Column(name = "VPr_Vou_ID")
    var voucherId: Long? = null,

    @Column(name = "VPr_Prd_ID")
    var productoId: Int? = null,

    @Column(name = "VPr_Paxs")
    var cantidadPaxs: Int? = null,

    @OneToOne(optional = true)
    @JoinColumn(name = "vpr_prd_id", insertable = false, updatable = false)
    var producto: Producto? = null

) : Auditable() {
    data class Builder(
        var voucherProductoId: Long? = null,
        var voucherId: Long? = null,
        var productoId: Int? = null,
        var cantidadPaxs: Int? = null,
        var producto: Producto? = null
    ) {
        fun voucherProductoId(voucherProductoId: Long?) = apply { this.voucherProductoId = voucherProductoId }
        fun voucherId(voucherId: Long?) = apply { this.voucherId = voucherId }
        fun productoId(productoId: Int?) = apply { this.productoId = productoId }
        fun cantidadPaxs(cantidadPaxs: Int?) = apply { this.cantidadPaxs = cantidadPaxs }
        fun producto(producto: Producto?) = apply {this.producto = producto}
        fun build() = VoucherProducto(voucherProductoId, voucherId, productoId, cantidadPaxs, producto)
    }
}
