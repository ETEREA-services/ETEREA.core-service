package eterea.core.api.rest.kotlin.exception

class ConceptoFacturadoException(articuloMovimientoId: Long) : RuntimeException("Cannot find ConceptoFacturado by ArticuloMovimiento= $articuloMovimientoId")