package eterea.core.service.kotlin.model

import eterea.core.service.model.Auditable
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import jakarta.persistence.UniqueConstraint
import java.util.UUID

@Entity
@Table(uniqueConstraints = [UniqueConstraint(columnNames = ["articuloId"])])
data class ArticuloListaPrecio (

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    var articuloListaPrecioId: UUID? = null,
    var articuloId: String? = null,
    var publicar: Byte = 0,

    @OneToOne(optional = true)
    @JoinColumn(name = "articuloId", insertable = false, updatable = false)
    var articulo: Articulo? = null

) : Auditable() {

    class Builder {
        private var articuloListaPrecioId: UUID? = null
        private var articuloId: String? = null
        private var publicar: Byte = 0
        private var articulo: Articulo? = null

        fun articuloListaPrecioId(articuloListaPrecioId: UUID?) = apply { this.articuloListaPrecioId = articuloListaPrecioId }
        fun articuloId(articuloId: String?) = apply { this.articuloId = articuloId }
        fun publicar(publicar: Byte) = apply { this.publicar = publicar }
        fun articulo(articulo: Articulo?) = apply { this.articulo = articulo }

        fun build() = ArticuloListaPrecio(
            articuloListaPrecioId = articuloListaPrecioId,
            articuloId = articuloId,
            publicar = publicar,
            articulo = articulo
        )
    }

    companion object {
        fun builder() = Builder()
    }
}
