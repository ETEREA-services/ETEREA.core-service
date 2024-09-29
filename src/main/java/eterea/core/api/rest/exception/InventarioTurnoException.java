package eterea.core.api.rest.exception;

public class InventarioTurnoException extends RuntimeException {

    public InventarioTurnoException(Integer inventarioTurnoId) {
        super("Cannot find InventarioTurno with id " + inventarioTurnoId);
    }

}
