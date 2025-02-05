package eterea.core.service.kotlin.model

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
@Table(uniqueConstraints = [UniqueConstraint(columnNames = ["rubroId"])])
data class RubroListaPrecio(

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    var rubroListaPrecioId: UUID? = null,

    var rubroId: Long? = null,
    var etiqueta: String = "",
    var publicar: Byte = 0,

    @OneToOne
    @JoinColumn(name = "rubroId", insertable = false, updatable = false)
    var rubro: Rubro? = null

) : Auditable()