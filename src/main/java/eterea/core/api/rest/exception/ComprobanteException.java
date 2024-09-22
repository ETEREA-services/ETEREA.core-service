/**
 * 
 */
package eterea.core.api.rest.exception;

/**
 * @author daniel
 *
 */
public class ComprobanteException extends RuntimeException {

	public ComprobanteException() {
		super("Cannot find Comprobante");
	}

	public ComprobanteException(Integer comprobanteId) {
		super("Cannot find Comprobante " + comprobanteId);
	}

}
