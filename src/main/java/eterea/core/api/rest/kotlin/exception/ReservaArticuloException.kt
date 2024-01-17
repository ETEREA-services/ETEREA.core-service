package eterea.core.api.rest.kotlin.exception

class ReservaArticuloException(reservaArticuloId: Long) : RuntimeException("Cannot find ReservaArticulo $reservaArticuloId")