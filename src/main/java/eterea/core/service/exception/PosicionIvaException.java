package eterea.core.service.exception;

public class PosicionIvaException extends RuntimeException {

    public PosicionIvaException(Integer posicionId) {
        super("Cannot find PosicionIva " + posicionId);
    }

}
