/**
 * 
 */
package eterea.core.service.hexagonal.comprobante.application.exception;

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
