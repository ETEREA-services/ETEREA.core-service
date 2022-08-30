/**
 * 
 */
package eterea.api.rest.exception;

/**
 * @author daniel
 *
 */
public class ClienteMovimientoNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1982482842715753589L;

	public ClienteMovimientoNotFoundException(Long clienteMovimientoId) {
		super("Cannot find ClienteMovimiento " + clienteMovimientoId);
	}

}
