package eterea.core.service.kotlin.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Entity
@Table(name = "productoweb_producto")
data class ProductoWebProducto(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "productoweb_producto_id")
        val id: Long,
        @ManyToOne
        @JoinColumn(name = "productoweb_id")
        val productoWeb: ProductoWeb,
        @ManyToOne
        @JoinColumn(name = "producto_id")
        val producto: Producto,
        @ManyToOne
        @JoinColumn(name = "tipodia_id")
        val tipoDia: TipoDia,
        @ManyToOne
        @JoinColumn(name = "tipopax_id")
        val tipoPax: TipoPax
) : Auditable()
