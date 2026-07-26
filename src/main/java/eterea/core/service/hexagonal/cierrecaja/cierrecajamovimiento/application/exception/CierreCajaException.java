package eterea.core.service.hexagonal.cierrecaja.cierrecajamovimiento.application.exception;

public class CierreCajaException extends RuntimeException {

    public CierreCajaException(Long cierreCajaId) {
        super("Cannot find CierreCaja " + cierreCajaId);
    }

}
