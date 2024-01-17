package eterea.core.api.rest.kotlin.model

import eterea.core.api.rest.model.Producto
import jakarta.persistence.*

@Entity
data class ProductoSku(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var productoSkuId: Long? = null,
    var sku: String = "",
    var lunes: Byte = 0,
    var martes: Byte = 0,
    var miercoles: Byte = 0,
    var jueves: Byte = 0,
    var viernes: Byte = 0,
    var sabado: Byte = 0,
    var domingo: Byte = 0,
    var feriado: Byte = 0,
    var productoIdPaxMayor: Int? = null,
    var productoIdPaxMenor: Int? = null,

    @OneToOne(optional = true)
    @JoinColumn(name = "productoIdPaxMayor", insertable = false, updatable = false)
    var productoPaxMayor: Producto? = null,

    @OneToOne(optional = true)
    @JoinColumn(name = "productoIdPaxMenor", insertable = false, updatable = false)
    var productoPaxMenor: Producto? = null

) : Auditable()
