/**
 * 
 */
package eterea.core.service.exception;

/**
 * @author daniel
 *
 */
public class ClienteMovimientoException extends RuntimeException {

	private static final long serialVersionUID = 1982482842715753589L;

	public ClienteMovimientoException(Long clienteMovimientoId) {
		super("Cannot find ClienteMovimiento " + clienteMovimientoId);
	}

}
