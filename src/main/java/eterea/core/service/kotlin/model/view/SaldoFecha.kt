package eterea.core.service.kotlin.model.view

import com.fasterxml.jackson.annotation.JsonFormat
import jakarta.persistence.Entity
import jakarta.persistence.Table
import jakarta.persistence.Id
import jakarta.persistence.Column
import org.hibernate.annotations.Immutable
import java.time.OffsetDateTime

import java.math.BigDecimal

@Entity
@Table(name = "vw_saldofecha")
@Immutable
data class SaldoFecha(

    @Id
    var uniqueId: String? = null,

    @Column(name = "depositoid")
    var centroStockId: Int? = null,

    @Column(name = "articuloid")
    var articuloId: String? = null,

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
    var fecha: OffsetDateTime? = null,

    var cantidad: BigDecimal = BigDecimal.ZERO

)
