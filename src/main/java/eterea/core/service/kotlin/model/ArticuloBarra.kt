package eterea.core.service.kotlin.model

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.json.JsonMapper
import eterea.core.service.model.Auditable
import eterea.core.service.tool.Jsonifier
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import jakarta.persistence.UniqueConstraint

@Entity
@Table(name = "articulosbarras", uniqueConstraints = [UniqueConstraint(columnNames = ["aba_codigo"])])
data class ArticuloBarra(

    @Id
    @Column(name = "clave")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var articuloBarraId: Long? = null,

    @Column(name = "aba_codigo")
    var codigoBarras: String? = null,

    @Column(name = "aba_art_id")
    var articuloId: String? = null,

    @OneToOne(optional = true)
    @JoinColumn(name = "aba_art_id", updatable = false, insertable = false)
    var articulo: Articulo? = null

) : Auditable() {

    fun jsonify(): String {
        return Jsonifier.builder(this).build()
    }

}
