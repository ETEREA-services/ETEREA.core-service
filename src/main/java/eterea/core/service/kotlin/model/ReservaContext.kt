package eterea.core.service.kotlin.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.math.BigDecimal

@Entity
data class ReservaContext(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var reservaContextId: Long? = null,
    var reservaId: Long? = null,
    var voucherId: Long? = null,
    var clienteMovimientoId: Long? = null,
    var orderNumberId: Long? = null,
    var facturadoFuera: Byte = 0,
    var facturaPendiente: Byte = 0,
    var facturaTries: Int = 0,
    var envioPendiente: Byte = 0,
    var envioTries: Int = 0,
    var diferenciaWeb: BigDecimal = BigDecimal.ZERO,

) : Auditable() {

    class Builder {
        var reservaContextId: Long? = null
        var reservaId: Long? = null
        var voucherId: Long? = null
        var clienteMovimientoId: Long? = null
        var orderNumberId: Long? = null
        var facturadoFuera: Byte = 0
        var facturaPendiente: Byte = 0
        var facturaTries: Int = 0
        var envioPendiente: Byte = 0
        var envioTries: Int = 0
        var diferenciaWeb: BigDecimal = BigDecimal.ZERO

        fun reservaContextId(reservaContextId: Long?) = apply { this.reservaContextId = reservaContextId }
        fun reservaId(reservaId: Long?) = apply { this.reservaId = reservaId }
        fun voucherId(voucherId: Long?) = apply { this.voucherId = voucherId }
        fun clienteMovimientoId(clienteMovimientoId: Long?) = apply { this.clienteMovimientoId = clienteMovimientoId }
        fun orderNumberId(orderNumberId: Long?) = apply { this.orderNumberId = orderNumberId }
        fun facturadoFuera(facturadoFuera: Byte) = apply { this.facturadoFuera = facturadoFuera }
        fun facturaPendiente(facturaPendiente: Byte) = apply { this.facturaPendiente = facturaPendiente }
        fun facturaTries(facturaTries: Int) = apply { this.facturaTries = facturaTries }
        fun envioPendiente(envioPendiente: Byte) = apply { this.envioPendiente = envioPendiente }
        fun envioTries(envioTries: Int) = apply { this.envioTries = envioTries }
        fun diferenciaWeb(diferenciaWeb: BigDecimal) = apply { this.diferenciaWeb = diferenciaWeb }

        fun build() = ReservaContext(
            reservaContextId,
            reservaId,
            voucherId,
            clienteMovimientoId,
            orderNumberId,
            facturadoFuera,
            facturaPendiente,
            facturaTries,
            envioPendiente,
            envioTries,
            diferenciaWeb
        )
    }
}
