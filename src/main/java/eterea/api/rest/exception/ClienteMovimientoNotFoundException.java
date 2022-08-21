/**
 * 
 */
package eterea.api.rest.exception;

/**
 * @author daniel
 *
 */
public class ClienteMovimientoNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1904801549397630866L;

	public ClienteMovimientoNotFoundException(Long clienteMovimientoId) {
		super("Cannot find ClienteMovimiento " + clienteMovimientoId);
	}

}
