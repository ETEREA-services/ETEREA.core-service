package eterea.core.service.kotlin.model

import com.fasterxml.jackson.annotation.JsonFormat
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
@Table(uniqueConstraints = [UniqueConstraint(columnNames = ["fecha", "orden", "item"])])
class CuentaMovimientoAperturaMoneda (
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    var cuentaMovimientoAperturaMonedaId: UUID? = null,
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
    var fecha: OffsetDateTime? = null,
    
    var orden: Int? = null,
    var item: Int? = null,
    var monedaId: Int? = null,
    var importe: BigDecimal = BigDecimal.ZERO
) : Auditable() {

    class Builder {
        private var cuentaMovimientoAperturaMonedaId: UUID? = null
        private var fecha: OffsetDateTime? = null
        private var orden: Int? = null
        private var item: Int? = null
        private var monedaId: Int? = null
        private var importe: BigDecimal = BigDecimal.ZERO

        fun cuentaMovimientoAperturaMonedaId(id: UUID?) = apply { this.cuentaMovimientoAperturaMonedaId = id }
        fun fecha(fecha: OffsetDateTime?) = apply { this.fecha = fecha }
        fun orden(orden: Int?) = apply { this.orden = orden }
        fun item(item: Int?) = apply { this.item = item }
        fun monedaId(monedaId: Int?) = apply { this.monedaId = monedaId }
        fun importe(importe: BigDecimal) = apply { this.importe = importe }

        fun build() = CuentaMovimientoAperturaMoneda(
            cuentaMovimientoAperturaMonedaId,
            fecha,
            orden,
            item,
            monedaId,
            importe
        )
    }

    companion object {
        fun builder() = Builder()
    }
}
