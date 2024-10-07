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

) : Auditable()
