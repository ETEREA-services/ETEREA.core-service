/**
 * 
 */
package eterea.api.rest.exception;

import java.text.MessageFormat;

/**
 * @author daniel
 *
 */
public class PuntoVentaException extends RuntimeException {

	private static final long serialVersionUID = -454246372863426272L;

	public PuntoVentaException(Integer puntoVenta) {
		super(MessageFormat.format("Cannot find PuntoVenta -> {0}", puntoVenta));
	}

}
