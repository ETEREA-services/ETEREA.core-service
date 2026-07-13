package eterea.core.service.hexagonal.tesoreria.valormovimiento.application.exception;

public class ValorMovimientoException extends RuntimeException {

    public ValorMovimientoException() {
        super("Cannot find ValorMovimiento");
    }

    public ValorMovimientoException(Long valorMovimientoId) {
        super("Cannot find ValorMovimiento " + valorMovimientoId);
    }

}
