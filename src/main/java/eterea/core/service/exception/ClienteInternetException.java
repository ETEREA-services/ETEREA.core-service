/**
 * 
 */
package eterea.core.service.exception;

/**
 * @author daniel
 *
 */
public class ClienteInternetException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8378505358801218295L;

	public ClienteInternetException(Long clienteId) {
		super("Cannot find ClienteInternet " + clienteId);
	}
}
