package eterea.core.service.kotlin.model

import jakarta.persistence.*

@Entity
@Table(name = "conceptosfact")
data class ConceptoFacturado(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "clave")
    var conceptoFacturadoId: Long? = null,

    @Column(name = "clavemovclie")
    var clienteMovimientoId: Long? = null,

    @Column(name = "nrolinea")
    var numeroLinea: Int? = null,

    @Column(name = "concepto")
    var concepto: String? = "",

    @Column(name = "clavedetartic")
    var articuloMovimientoId: Long? = null,

) : Auditable()
