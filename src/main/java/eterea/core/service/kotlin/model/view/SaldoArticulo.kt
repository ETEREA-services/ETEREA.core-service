package eterea.core.service.kotlin.model.view

import jakarta.persistence.Entity
import jakarta.persistence.Table
import jakarta.persistence.Id
import jakarta.persistence.Column

import java.math.BigDecimal

@Entity
@Table(name = "vw_saldoarticulo")
data class SaldoArticulo(

    @Id
    var uniqueId: String? = null,

    @Column(name = "depositoid")
    var centroStockId: Int? = null,

    @Column(name = "articuloid")
    var articuloId: String? = null,

    var cantidad: BigDecimal = BigDecimal.ZERO

)
