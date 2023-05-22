/**
 * 
 */
package eterea.core.api.rest.exception;

/**
 * @author daniel
 *
 */
public class MonedaException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6690124957053098521L;

	public MonedaException(Integer monedaId) {
		super("Cannot find Moneda " + monedaId);
	}

}
