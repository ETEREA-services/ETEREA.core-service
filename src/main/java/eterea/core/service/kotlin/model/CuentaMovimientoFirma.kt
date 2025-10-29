package eterea.core.service.kotlin.model

import com.fasterxml.jackson.annotation.JsonFormat
import eterea.core.service.model.Auditable
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.OffsetDateTime

@Entity
@Table(name = "movconfirma")
data class CuentaMovimientoFirma(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mcf_firma")
    var cuentaMovimientoFirmaId: Long? = null,

    @Column(name = "mcf_fecha")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
    var fechaContable: OffsetDateTime? = null,

    @Column(name = "mcf_nrocomp")
    var ordenContable: Int? = null,

    @Column(name = "mcf_timestamp")
    var timestamp: OffsetDateTime? = null

) : Auditable()
