/**
 * 
 */
package eterea.core.service.exception;

import java.io.Serial;

/**
 * @author daniel
 *
 */
public class ClienteMovimientoException extends RuntimeException {

	@Serial
	private static final long serialVersionUID = 1982482842715753589L;

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
