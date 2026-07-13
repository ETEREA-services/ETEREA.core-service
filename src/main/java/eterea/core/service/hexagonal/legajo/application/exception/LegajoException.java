package eterea.core.service.hexagonal.legajo.application.exception;

public class LegajoException extends RuntimeException {

    public LegajoException(Integer legajoId) {
        super("Legajo no encontrado: " + legajoId);
    }
}
