/**
 * 
 */
package eterea.api.rest.exception;

import java.text.MessageFormat;

/**
 * @author daniel
 *
 */
public class ClienteMovimientoNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1982482842715753589L;

	public ClienteMovimientoNotFoundException(Long clienteMovimientoId) {
		super("Cannot find ClienteMovimiento " + clienteMovimientoId);
	}

	public ClienteMovimientoNotFoundException(Integer comprobanteId, Integer puntoVenta, Long numeroComprobante) {
		super(MessageFormat.format("Cannot find ClienteMovimiento {0}/{1}-{2}", comprobanteId, puntoVenta,
				numeroComprobante));
	}

}
