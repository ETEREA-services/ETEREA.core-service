package eterea.core.api.rest.kotlin.model.dto

import eterea.core.api.rest.kotlin.model.ClienteMovimiento
import eterea.core.api.rest.kotlin.model.ReservaOrigen
import eterea.core.api.rest.kotlin.model.Voucher

data class ProgramaDiaDTO(

    var vouchers: List<Voucher?>? = null,
    var reservaOrigens: List<ReservaOrigen?>? = null,
    var clienteMovimientos: List<ClienteMovimiento?>? = null

)
