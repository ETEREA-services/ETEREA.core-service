package eterea.core.service.exception;

public class LegajoException extends RuntimeException {

    public LegajoException(Integer legajoId) {
        super("Legajo no encontrado: " + legajoId);
    }
}
