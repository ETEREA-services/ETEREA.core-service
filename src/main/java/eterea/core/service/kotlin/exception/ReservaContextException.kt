package eterea.core.service.kotlin.exception

class ReservaContextException(type: String, value: Long) : RuntimeException("Cannot find ReservaContext ($type) -> $value") {

    constructor(reservaContextId: Long) : this("", reservaContextId)

}