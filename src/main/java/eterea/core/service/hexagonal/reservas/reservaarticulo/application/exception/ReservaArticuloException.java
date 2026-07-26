package eterea.core.service.hexagonal.reservas.reservaarticulo.application.exception;

public class ReservaArticuloException extends RuntimeException {

    public ReservaArticuloException() {
        super("Cannot find ReservaArticulo");
    }

    public ReservaArticuloException(Long reservaArticuloId) {
        super("Cannot find ReservaArticulo " + reservaArticuloId);
    }

}
