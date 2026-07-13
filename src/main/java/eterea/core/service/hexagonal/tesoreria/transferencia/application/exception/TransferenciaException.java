package eterea.core.service.hexagonal.tesoreria.transferencia.application.exception;

public class TransferenciaException extends RuntimeException {

    public TransferenciaException(int negocioIdDesde, int negocioIdHasta, long numeroTransferencia) {
        super("No se encontró la transferencia con los siguientes parámetros: "
                + "negocioIdDesde: " + negocioIdDesde + ", "
                + "negocioIdHasta: " + negocioIdHasta + ", "
                + "numeroTransferencia: " + numeroTransferencia);
    }

}
