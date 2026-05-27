package eterea.core.service.hexagonal.articulomovimiento.application.exception;

public class ArticuloMovimientoException extends RuntimeException {
    public ArticuloMovimientoException(Long articuloMovimientoId) {
        super("Cannot find ArticuloMovimiento " + articuloMovimientoId);
    }
}
