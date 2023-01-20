/**
 * 
 */
package eterea.api.rest.exception;

/**
 * @author daniel
 *
 */
public class ComprobanteException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5757903343280334045L;

	public ComprobanteException(Integer comprobanteId) {
		super("Cannot find Comprobante " + comprobanteId);
	}

}
