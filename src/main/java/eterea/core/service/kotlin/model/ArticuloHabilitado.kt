package eterea.core.service.kotlin.model

import com.fasterxml.jackson.annotation.JsonFormat
import eterea.core.service.model.Auditable
import jakarta.persistence.*
import java.time.OffsetDateTime

@Entity
@Table(
    name = "articulohabil",
    uniqueConstraints = [UniqueConstraint(columnNames = ["aha_fecha", "aha_serie", "aha_inicial"])]
)
data class ArticuloHabilitado(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "aha_id")
    var articuloHabilitadoId: Long? = null,

    @Column(name = "aha_fecha")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
    var fechaHabilitacion: OffsetDateTime? = null,

    @Column(name = "aha_serie")
    var serie: String = "",

    @Column(name = "aha_inicial")
    var numeroInicial: Long = 0,

    @Column(name = "aha_final")
    var numeroFinal: Long = 0,

    @Column(name = "aha_art_id")
    var articuloId: String? = null

) : Auditable()
