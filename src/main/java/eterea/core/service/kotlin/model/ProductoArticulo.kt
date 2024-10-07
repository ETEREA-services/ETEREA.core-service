package eterea.core.service.kotlin.model

import jakarta.persistence.*

@Entity
@Table(name = "productoarticulo", uniqueConstraints = [UniqueConstraint(columnNames = ["par_prd_id", "par_art_id"])])
data class ProductoArticulo(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "par_id")
    var productoarticuloId: Long? = null,

    @Column(name = "par_neg_id")
    var negocioId: Int? = null,

    @Column(name = "par_prd_id")
    var productoId: Int? = null,

    @Column(name = "par_art_id")
    var articuloId: String? = null,

    @OneToOne(optional = true)
    @JoinColumn(name = "par_prd_id", insertable = false, updatable = false)
    var producto: Producto? = null,

    @OneToOne(optional = true)
    @JoinColumn(name = "par_art_id", insertable = false, updatable = false)
    var articulo: Articulo? = null

) : Auditable()
