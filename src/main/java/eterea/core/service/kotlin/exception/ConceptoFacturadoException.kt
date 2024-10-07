package eterea.core.service.kotlin.exception

class ConceptoFacturadoException(articuloMovimientoId: Long) : RuntimeException("Cannot find ConceptoFacturado by ArticuloMovimiento= $articuloMovimientoId")