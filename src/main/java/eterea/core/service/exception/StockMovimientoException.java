package eterea.core.service.exception;

public class StockMovimientoException extends RuntimeException {

    public StockMovimientoException(Integer comprobanteId) {
        super("Stock movimiento with comprobanteId " + comprobanteId + " not found");
    }

    public StockMovimientoException(Long stockMovimientoId) {
        super("Stock movimiento with stockMovimientoId " + stockMovimientoId + " not found");
    }
}
