package eterea.core.service.kotlin.model

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.json.JsonMapper
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import jakarta.persistence.UniqueConstraint
import java.math.BigDecimal
import java.time.OffsetDateTime

@Entity
@Table(name = "transferencias", uniqueConstraints = [
    UniqueConstraint(columnNames = ["tra_dneg_id", "tra_hneg_id", "tra_id"])
])
data class Transferencia (

    @Id
    @Column(name = "clave")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var transferenciaId: Long? = null,

    @Column(name = "tra_dneg_id")
    var negocioIdDesde: Int? = null,

    @Column(name = "tra_hneg_id")
    var negocioIdHasta: Int? = null,

    @Column(name = "tra_id")
    var numeroTransferencia: Long? = null,

    @Column(name = "tra_fecha")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
    var fecha: OffsetDateTime? = null,

    @Column(name = "tra_total")
    var total: BigDecimal = BigDecimal.ZERO,

    @Column(name = "tra_nrocontable")
    var ordenContable: Int? = null,

    @Column(name = "tra_transferido")
    var transferido: Byte = 0,

    @Column(name = "tra_cmp_id")
    var comprobanteId: Int? = null,

    @Column(name = "tra_fechaotro")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
    var fechaOtro: OffsetDateTime? = null,

    @OneToOne(optional = true)
    @JoinColumn(name = "tra_cmp_id", insertable = false, updatable = false)
    var comprobante: Comprobante? = null

) : Auditable() {

    fun jsonify(): String {
        try {
            return JsonMapper
                .builder()
                .findAndAddModules()
                .build()
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(this)
        } catch (e : JsonProcessingException) {
            return "jsonify error -> ${e.message}"
        }
    }

    class Builder {
        private var transferenciaId: Long? = null
        private var negocioIdDesde: Int? = null
        private var negocioIdHasta: Int? = null
        private var numeroTransferencia: Long? = null
        private var fecha: OffsetDateTime? = null
        private var total: BigDecimal = BigDecimal.ZERO
        private var ordenContable: Int? = null
        private var transferido: Byte = 0
        private var comprobanteId: Int? = null
        private var fechaOtro: OffsetDateTime? = null
        private var comprobante: Comprobante? = null

        fun transferenciaId(id: Long?) = apply { this.transferenciaId = id }
        fun negocioIdDesde(id: Int?) = apply { this.negocioIdDesde = id }
        fun negocioIdHasta(id: Int?) = apply { this.negocioIdHasta = id }
        fun numeroTransferencia(numero: Long?) = apply { this.numeroTransferencia = numero }
        fun fecha(fecha: OffsetDateTime?) = apply { this.fecha = fecha }
        fun total(total: BigDecimal) = apply { this.total = total }
        fun ordenContable(orden: Int?) = apply { this.ordenContable = orden }
        fun transferido(transferido: Byte) = apply { this.transferido = transferido }
        fun comprobanteId(id: Int?) = apply { this.comprobanteId = id }
        fun fechaOtro(fecha: OffsetDateTime?) = apply { this.fechaOtro = fecha }
        fun comprobante(comprobante: Comprobante?) = apply { this.comprobante = comprobante }

        fun build() = Transferencia(
            transferenciaId,
            negocioIdDesde,
            negocioIdHasta,
            numeroTransferencia,
            fecha,
            total,
            ordenContable,
            transferido,
            comprobanteId,
            fechaOtro,
            comprobante
        )
    }

    companion object {
        fun builder() = Builder()
    }

}
