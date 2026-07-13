package eterea.core.service.hexagonal.stock.stockmovimiento.application.exception;

public class StockMovimientoException extends RuntimeException {

    public StockMovimientoException() {
        super("Cannot find StockMovimiento");
    }

    public StockMovimientoException(Integer comprobanteId) {
        super("Cannot find StockMovimiento with comprobanteId " + comprobanteId);
    }

    public StockMovimientoException(Long stockMovimientoId) {
        super("Cannot find StockMovimiento with stockMovimientoId " + stockMovimientoId);
    }

}
