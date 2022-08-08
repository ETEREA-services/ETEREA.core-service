/**
 * 
 */
package eterea.api.rest.exception;

/**
 * @author daniel
 *
 */
public class ClienteInternetNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8378505358801218295L;

	public ClienteInternetNotFoundException(Long clienteId) {
		super("Cannot find ClienteInternet " + clienteId);
	}
}
