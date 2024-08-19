package eterea.core.api.rest.kotlin.model

import com.fasterxml.jackson.annotation.JsonFormat
import jakarta.persistence.*
import java.sql.Time
import java.time.OffsetDateTime

@Entity
@Table(
    name = "legajoregistro",
    uniqueConstraints = [UniqueConstraint(columnNames = ["lre_leg_id", "lre_fecha", "lre_hora"])]
)
data class LegajoRegistro(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lre_id")
    var legajoRegistroId: Long? = null,

    @Column(name = "lre_leg_id")
    var legajoId: Int? = null,

    @Column(name = "lre_fecha")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
    var fecha: OffsetDateTime? = null,

    @Column(name = "lre_hora")
    var hora: Time? = null,

    @Column(name = "lre_salida")
    var salida: Byte = 0,

    @Column(name = "lre_ori_id")
    var origenId: Int? = null

) : Auditable()
