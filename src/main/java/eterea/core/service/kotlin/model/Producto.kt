package eterea.core.service.kotlin.model

import eterea.core.service.model.Auditable
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table
data class Producto(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prd_id")
    var productoId: Int? = null,

    @Column(name = "prd_nombre")
    var nombre: String = "",

    @Column(name = "prd_observaciones")
    var observaciones: String = "",

    @Column(name = "prd_deshabilitado")
    var deshabilitado: Byte = 0,

    var traslado: Byte = 0,
    var puntoEncuentro: Byte = 0,
    var ventaMostrador: Byte = 0,
    var ventaInternet: Byte = 0

) : Auditable()
