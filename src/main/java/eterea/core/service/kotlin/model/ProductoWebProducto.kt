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
@Table(name = "producto_web_producto")
data class ProductoWebProducto(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "producto_web_producto_id")
        val id: Long,
        @Column(name = "producto_web_id")
        @ManyToOne
        @JoinColumn(name = "productoweb_id")
        val productoWeb: ProductoWeb,
        @Column(name = "producto_id")
        @ManyToOne
        @JoinColumn(name = "producto_id")
        val producto: Producto,
        @Column(name = "tipodia_id")
        @ManyToOne
        @JoinColumn(name = "tipodia_id")
        val tipoDia: TipoDia,
        @Column(name = "tipopax_id")
        @ManyToOne
        @JoinColumn(name = "tipopax_id")
        val tipoPax: TipoPax
) : Auditable()
