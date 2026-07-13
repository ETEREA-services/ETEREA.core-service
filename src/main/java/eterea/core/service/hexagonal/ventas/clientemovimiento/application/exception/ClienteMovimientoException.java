package eterea.core.service.hexagonal.ventas.clientemovimiento.application.exception;

public class ClienteMovimientoException extends RuntimeException {

    public ClienteMovimientoException() {
        super("Cannot find ClienteMovimiento");
    }

    public ClienteMovimientoException(Long clienteMovimientoId) {
        super("Cannot find ClienteMovimiento " + clienteMovimientoId);
    }

    public ClienteMovimientoException(Integer comprobanteId, Integer puntoVenta, Long numeroComprobante) {
        super("Cannot find ClienteMovimiento " + comprobanteId + "." + puntoVenta + "." + numeroComprobante);
    }

    public ClienteMovimientoException(String letraComprobante, Byte debita, Integer puntoVenta, Long numeroComprobante) {
        super("Cannot find ClienteMovimiento " + letraComprobante + "." + debita + "." + puntoVenta + "." + numeroComprobante);
    }
}
