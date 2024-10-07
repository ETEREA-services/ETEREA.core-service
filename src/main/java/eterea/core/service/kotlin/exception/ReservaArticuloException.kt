package eterea.core.service.kotlin.exception

class ReservaArticuloException(reservaArticuloId: Long) : RuntimeException("Cannot find ReservaArticulo $reservaArticuloId")