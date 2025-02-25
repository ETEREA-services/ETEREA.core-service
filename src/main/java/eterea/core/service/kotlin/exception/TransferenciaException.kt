package eterea.core.service.kotlin.exception

class TransferenciaException(
    val negocioIdDesde: Int,
    val negocioIdHasta: Int,
    val numeroTransferencia: Long
) : RuntimeException(
    "No se encontró la transferencia con los siguientes parámetros: " +
            "negocioIdDesde: $negocioIdDesde, " +
            "negocioIdHasta: $negocioIdHasta, " +
            "numeroTransferencia: $numeroTransferencia"
)