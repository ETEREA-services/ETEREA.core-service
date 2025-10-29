package eterea.core.service.kotlin.model

import com.fasterxml.jackson.annotation.JsonFormat
import eterea.core.service.model.Auditable
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.persistence.UniqueConstraint
import java.math.BigDecimal
import java.time.OffsetDateTime
import java.util.UUID

@Entity
@Table(uniqueConstraints = [UniqueConstraint(columnNames = ["fecha", "monedaIdOrigen", "monedaIdDestino"])])
data class MonedaCotizacion(

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    var monedaCotizacionId: UUID? = null,

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
    var fecha: OffsetDateTime? = null,

    var monedaIdOrigen: Int? = null,
    var cotizacionOrigen: Double? = null,
    var copiaOrigen: Byte = 0,
    var monedaIdDestino: Int? = null,
    var cotizacionDestino: Double? = null,
    var copiaDestino: Byte = 0,
    var coeficiente: Double? = null

) : Auditable() {

    data class Builder(
        var monedaCotizacionId: UUID? = null,
        var fecha: OffsetDateTime? = null,
        var monedaIdOrigen: Int? = null,
        var cotizacionOrigen: Double? = null,
        var copiaOrigen: Byte = 0,
        var monedaIdDestino: Int? = null,
        var cotizacionDestino: Double? = null,
        var copiaDestino: Byte = 0,
        var coeficiente: Double? = null
    ) {
        fun monedaCotizacionId(monedaCotizacionId: UUID?) = apply { this.monedaCotizacionId = monedaCotizacionId }
        fun fecha(fecha: OffsetDateTime?) = apply { this.fecha = fecha }
        fun monedaIdOrigen(monedaIdOrigen: Int?) = apply { this.monedaIdOrigen = monedaIdOrigen }
        fun cotizacionOrigen(cotizacionOrigen: Double?) = apply { this.cotizacionOrigen = cotizacionOrigen }
        fun copiaOrigen(copiaOrigen: Byte) = apply { this.copiaOrigen = copiaOrigen }
        fun monedaIdDestino(monedaIdDestino: Int?) = apply { this.monedaIdDestino = monedaIdDestino }
        fun cotizacionDestino(cotizacionDestino: Double?) = apply { this.cotizacionDestino = cotizacionDestino }
        fun copiaDestino(copiaDestino: Byte) = apply { this.copiaDestino = copiaDestino }
        fun coeficiente(coeficiente: Double?) = apply { this.coeficiente = coeficiente }

        fun build() = MonedaCotizacion(
            monedaCotizacionId,
            fecha,
            monedaIdOrigen,
            cotizacionOrigen,
            copiaOrigen,
            monedaIdDestino,
            cotizacionDestino,
            copiaDestino,
            coeficiente
        )
    }
}
