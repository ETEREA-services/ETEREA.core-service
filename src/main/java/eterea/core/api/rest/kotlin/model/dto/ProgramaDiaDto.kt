package eterea.core.api.rest.kotlin.model.dto

import eterea.core.api.rest.kotlin.model.ClienteMovimiento
import eterea.core.api.rest.kotlin.model.ReservaOrigen
import eterea.core.api.rest.kotlin.model.Voucher

data class ProgramaDiaDto(
    var vouchers: List<Voucher?>? = null,
    var reservaOrigens: List<ReservaOrigen?>? = null,
    var clienteMovimientos: List<ClienteMovimiento?>? = null,
    var errorMessage: String = ""
) {
    data class Builder(
        var vouchers: List<Voucher?>? = null,
        var reservaOrigens: List<ReservaOrigen?>? = null,
        var clienteMovimientos: List<ClienteMovimiento?>? = null,
        var errorMessage: String = ""
    ) {
        fun vouchers(vouchers: List<Voucher?>?) = apply { this.vouchers = vouchers }
        fun reservaOrigens(reservaOrigens: List<ReservaOrigen?>?) = apply { this.reservaOrigens = reservaOrigens }
        fun clienteMovimientos(clienteMovimientos: List<ClienteMovimiento?>?) =
            apply { this.clienteMovimientos = clienteMovimientos }

        fun errorMessage(errorMessage: String) = apply { this.errorMessage = errorMessage }

        fun build() = ProgramaDiaDto(vouchers, reservaOrigens, clienteMovimientos, errorMessage)
    }
}
