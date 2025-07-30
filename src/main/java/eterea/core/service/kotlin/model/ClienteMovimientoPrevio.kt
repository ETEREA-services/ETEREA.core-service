package eterea.core.service.kotlin.model

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.json.JsonMapper
import jakarta.persistence.*
import java.math.BigDecimal
import java.time.OffsetDateTime

@Entity
@Table(name = "clientemovimientoprevio")
data class ClienteMovimientoPrevio(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "clientemovimientoprevio_id")
    var clienteMovimientoPrevioId: Long? = null,

    var enConstruccion: Byte = 0,
    var tieneArticulos: Byte = 0,
    var ticketImpreso: Byte = 0,
    var clienteId: Long? = null,
    var puntoVenta: Int? = null,

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
    var fecha: OffsetDateTime? = null,

    var importe: BigDecimal = BigDecimal.ZERO,

    @Column(name = "clientemovimiento_id")
    var clienteMovimientoId: Long? = null,

    @OneToOne(optional = true)
    @JoinColumn(name = "clienteId", insertable = false, updatable = false)
    var cliente: Cliente? = null,

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "clientemovimientoprevio_id", insertable = false, updatable = false)
    var articuloMovimientoPrevios: List<ArticuloMovimientoPrevio>? = null

) : Auditable() {

    fun jsonify(): String {
        try {
            return JsonMapper
                .builder()
                .findAndAddModules()
                .build()
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(this)
        } catch (e: JsonProcessingException) {
            return "jsonify error -> ${e.message}"
        }
    }

}
