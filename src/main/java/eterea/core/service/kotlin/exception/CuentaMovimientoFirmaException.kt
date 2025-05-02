package eterea.core.service.kotlin.exception

import java.time.OffsetDateTime

class CuentaMovimientoFirmaException : RuntimeException {

    constructor(fechaContable: OffsetDateTime, ordenContable: Int) : super("No existe firma para fecha ${fechaContable} y orden ${ordenContable}")

}