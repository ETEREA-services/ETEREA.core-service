package eterea.core.service.kotlin.model

import com.fasterxml.jackson.annotation.JsonFormat
import jakarta.persistence.*
import java.math.BigDecimal
import java.time.OffsetDateTime

@Entity
@Table(name = "articulofecha", uniqueConstraints = [UniqueConstraint(columnNames = ["articulo_id", "fecha"])])
data class ArticuloFecha(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "articulofecha_id")
    var articuloFechaId: Long? = null,

    var articuloId: String = "",

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
    var fecha: OffsetDateTime? = null,

    var precioUsd: BigDecimal = BigDecimal.ZERO,
    var precioArs: BigDecimal = BigDecimal.ZERO

) : Auditable() {

    class Builder {
        private var articuloFechaId: Long? = null
        private var articuloId: String = ""
        private var fecha: OffsetDateTime? = null
        private var precioUsd: BigDecimal = BigDecimal.ZERO
        private var precioArs: BigDecimal = BigDecimal.ZERO

        fun articuloFechaId(articuloFechaId: Long?) = apply { this.articuloFechaId = articuloFechaId }
        fun articuloId(articuloId: String) = apply { this.articuloId = articuloId }
        fun fecha(fecha: OffsetDateTime?) = apply { this.fecha = fecha }
        fun precioUsd(precioUsd: BigDecimal) = apply { this.precioUsd = precioUsd }
        fun precioArs(precioArs: BigDecimal) = apply { this.precioArs = precioArs }

        fun build() = ArticuloFecha(
            articuloFechaId = articuloFechaId,
            articuloId = articuloId,
            fecha = fecha,
            precioUsd = precioUsd,
            precioArs = precioArs
        )
    }
}
