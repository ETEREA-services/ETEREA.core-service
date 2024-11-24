package eterea.core.service.kotlin.model.view

import com.fasterxml.jackson.annotation.JsonFormat
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.springframework.data.annotation.Immutable
import java.math.BigDecimal
import java.time.OffsetDateTime

@Entity
@Table(name = "vw_asientos")
@Immutable
data class AsientoView(

    @Id
    var uniqueId: String? = null,

    var negocioId: Int? = null,

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
    var fecha: OffsetDateTime? = null,

    @Column(name = "nrocomp")
    var orden: Int = 0,

    @Column(name = "cgotcomp")
    var comprobanteId: Int? = null,

    var concepto: String = "",

    @Column(name = "cic_id")
    var cierreCajaId: Long? = null,
    var debe: BigDecimal = BigDecimal.ZERO,
    var haber: BigDecimal = BigDecimal.ZERO

)
