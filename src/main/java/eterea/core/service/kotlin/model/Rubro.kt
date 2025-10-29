package eterea.core.service.kotlin.model

import eterea.core.service.model.Auditable
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "articulosrubros")
data class Rubro(

    @Id
    @Column(name = "codigo")
    var rubroId: Long? = null,

    @Column(name = "aru_neg_id")
    var negocioId: Int? = null,

    var descripcion: String? = null,

    @Column(name = "unegocio")
    var unidadNegocio: Int? = null,

    @Column(name = "aru_restaurant")
    var restaurant: Byte = 0,

    @Column(name = "aru_prv_id")
    var proveedorId: Long? = null,

) : Auditable()
