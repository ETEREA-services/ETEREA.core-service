package eterea.core.service.kotlin.exception

class StockMovimientoException(comprobanteId: Int) : RuntimeException("Cannot find StockMovimiento for Comprobante -> $comprobanteId ") {
}