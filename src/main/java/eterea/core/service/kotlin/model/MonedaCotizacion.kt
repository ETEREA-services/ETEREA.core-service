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
@Table(uniqueConstraints = [UniqueConstraint(columnNames = ["monedaId", "fecha"])])
data class MonedaCotizacion(

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    var monedaCotizacionId: UUID? = null,
    var monedaId: Int? = null,

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
    var fecha: OffsetDateTime? = null,
    var cotizacion: BigDecimal? = null,
    var copia: Byte = 0

) : Auditable()
