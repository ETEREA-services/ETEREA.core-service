package eterea.core.service.kotlin.model

import com.fasterxml.jackson.annotation.JsonFormat
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.math.BigDecimal
import java.time.OffsetDateTime

@Entity
@Table
data class Asiento(

    @Id @Column(name = "asi_id")
    var asientoId: BigDecimal? = null,

    @Column(name = "asi_fecha")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
    var fecha: OffsetDateTime? = null,

    @Column(name = "asi_usuario")
    var usuario: String? = null,

    @Column(name = "asi_ip")
    var ipAdress: String? = null

) : Auditable()
