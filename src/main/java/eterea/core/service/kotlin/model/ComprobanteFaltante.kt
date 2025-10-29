package eterea.core.service.kotlin.model

import com.fasterxml.jackson.annotation.JsonFormat
import eterea.core.service.model.Auditable
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import jakarta.persistence.UniqueConstraint
import java.time.OffsetDateTime

@Entity
@Table(name = "comprobfaltante",
    uniqueConstraints = [UniqueConstraint(columnNames = ["cfa_neg_id", "cfa_cmp_id", "cfa_fecha", "cfa_prefijo", "cfa_numero"])])
data class ComprobanteFaltante(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cfa_id")
    var comprobanteFaltanteId: Long? = null,

    @Column(name = "cfa_neg_id")
    var negocioId: Int? = null,

    @Column(name = "cfa_cmp_id")
    var comprobanteId: Int? = null,

    @Column(name = "cfa_fecha")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
    var fecha: OffsetDateTime? = null,

    @Column(name = "cfa_prefijo")
    var prefijo: Int = 0,

    @Column(name = "cfa_numero")
    var numero: Long = 0,

    @OneToOne(optional = true)
    @JoinColumn(name = "cfa_neg_id", insertable = false, updatable = false)
    var negocio: Negocio? = null,

    @OneToOne(optional = true)
    @JoinColumn(name = "cfa_cmp_id", insertable = false, updatable = false)
    var comprobante: Comprobante? = null

) : Auditable() {

    data class Builder(
        var comprobanteFaltanteId: Long? = null,
        var negocioId: Int? = null,
        var comprobanteId: Int? = null,
        var fecha: OffsetDateTime? = null,
        var prefijo: Int = 0,
        var numero: Long = 0,
        var negocio: Negocio? = null,
        var comprobante: Comprobante? = null
    ) {
        fun comprobanteFaltanteId(comprobanteFaltanteId: Long?) = apply { this.comprobanteFaltanteId = comprobanteFaltanteId }
        fun negocioId(negocioId: Int?) = apply { this.negocioId = negocioId }
        fun comprobanteId(comprobanteId: Int?) = apply { this.comprobanteId = comprobanteId }
        fun fecha(fecha: OffsetDateTime?) = apply { this.fecha = fecha }
        fun prefijo(prefijo: Int) = apply { this.prefijo = prefijo }
        fun numero(numero: Long) = apply { this.numero = numero }
        fun negocio(negocio: Negocio?) = apply { this.negocio = negocio }
        fun comprobante(comprobante: Comprobante?) = apply { this.comprobante = comprobante }

        fun build() = ComprobanteFaltante(
            comprobanteFaltanteId,
            negocioId,
            comprobanteId,
            fecha,
            prefijo,
            numero,
            negocio,
            comprobante
        )
    }

    companion object {
        fun builder() = Builder()
    }
}
