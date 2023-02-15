/**
 * 
 */
package eterea.api.rest.exception;

import java.text.MessageFormat;

/**
 * @author daniel
 *
 */
public class CuentaMovimientoException extends RuntimeException {

	private static final long serialVersionUID = -2204143864368166841L;

	public CuentaMovimientoException(Long cuentaMovimientoId) {
		super(MessageFormat.format("Cannot found CuentaMovimiento -> {0}", cuentaMovimientoId));
	}

}
