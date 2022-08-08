/**
 * 
 */
package eterea.api.rest.exception;

/**
 * @author daniel
 *
 */
public class MonedaNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6690124957053098521L;

	public MonedaNotFoundException(Integer monedaId) {
		super("Cannot find Moneda " + monedaId);
	}

}
