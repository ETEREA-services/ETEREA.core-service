package eterea.core.service.kotlin.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "proveedores")
data class Proveedor(

    @Id
    @Column(name = "codigo")
    var proveedorId: Long? = null,

    @Column(name = "razon")
    var razonSocial: String = "",

    @Column(name = "Prv_Neg_ID")
    var negocioId: Int? = null,

    var cuit: String = "",
    var domicilio: String = "",

    @Column(name = "tel")
    var telefono: String = "",

    var fax: String = "",
    var email: String = "",
    var posicion: Int = 0,
    var celular: String = "",

    @Column(name = "nroinscib")
    var numeroInscripcionIngresosBrutos: String = "",

    @Column(name = "ibcontribuyente_id")
    var ingresosBrutosContribuyenteId: Int? = null,

    @Column(name = "prv_prc_id")
    var proveedorCategoriaId: Int? = null,

    @Column(name = "ibcategoria_id")
    var inressosBrutosCategoriaId: Int? = null,

    @Column(name = "prv_rep_codigo")
    var reparticionId: Int? = null,

    var transporte: Byte = 0,

) : Auditable()
