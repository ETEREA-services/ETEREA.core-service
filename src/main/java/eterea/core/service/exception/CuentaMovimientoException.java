/**
 * 
 */
package eterea.core.service.exception;

import java.text.MessageFormat;
import java.time.OffsetDateTime;

/**
 * @author daniel
 *
 */
public class CuentaMovimientoException extends RuntimeException {

	private static final long serialVersionUID = -2204143864368166841L;

	public CuentaMovimientoException(Long cuentaMovimientoId) {
		super(MessageFormat.format("Cannot found CuentaMovimiento -> {0}", cuentaMovimientoId));
	}

	public CuentaMovimientoException(OffsetDateTime fecha, Integer orden) {
		super(MessageFormat.format("Cannot found CuentaMovimiento -> {0}-{1}", fecha, orden));
	}

    public CuentaMovimientoException(OffsetDateTime fecha) {
		super(MessageFormat.format("Cannot found CuentaMovimiento -> {0}", fecha));
    }
}
