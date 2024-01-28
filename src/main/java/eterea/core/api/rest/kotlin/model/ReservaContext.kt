package eterea.core.api.rest.kotlin.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
data class ReservaContext(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var reservaContextId: Long? = null,
    var reservaId: Long? = null,
    var voucherId: Long? = null,
    var clienteMovimientoId: Long? = null,
    var orderNumberId: Long? = null,
    var facturaPendiente: Byte = 0,
    var facturaTries: Int = 0,
    var envioPendiente: Byte = 0,
    var envioTries: Int = 0

) : Auditable() {

    class Builder {
        var reservaContextId: Long? = null
        var reservaId: Long? = null
        var voucherId: Long? = null
        var clienteMovimientoId: Long? = null
        var orderNumberId: Long? = null
        var facturaPendiente: Byte = 0
        var facturaTries: Int = 0
        var envioPendiente: Byte = 0
        var envioTries: Int = 0

        fun reservaContextId(reservaContextId: Long?) = apply { this.reservaContextId = reservaContextId }
        fun reservaId(reservaId: Long?) = apply { this.reservaId = reservaId }
        fun voucherId(voucherId: Long?) = apply { this.voucherId = voucherId }
        fun clienteMovimientoId(clienteMovimientoId: Long?) = apply { this.clienteMovimientoId = clienteMovimientoId }
        fun orderNumberId(orderNumberId: Long?) = apply { this.orderNumberId = orderNumberId }
        fun facturaPendiente(facturaPendiente: Byte) = apply { this.facturaPendiente = facturaPendiente }
        fun facturaTries(facturaTries: Int) = apply { this.facturaTries = facturaTries }
        fun envioPendiente(envioPendiente: Byte) = apply { this.envioPendiente = envioPendiente }
        fun envioTries(envioTries: Int) = apply { this.envioTries = envioTries }

        fun build() = ReservaContext(
            reservaContextId,
            reservaId,
            voucherId,
            clienteMovimientoId,
            orderNumberId,
            facturaPendiente,
            facturaTries,
            envioPendiente,
            envioTries
        )
    }
}
