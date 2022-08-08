/**
 * 
 */
package eterea.api.rest.exception;

import java.time.OffsetDateTime;

/**
 * @author daniel
 *
 */
public class ArticuloFechaNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5015112641151317112L;

	public ArticuloFechaNotFoundException(Long articulofechaId) {
		super("Cannot find ArticuloFecha " + articulofechaId);
	}

	public ArticuloFechaNotFoundException(String articuloId, OffsetDateTime fecha) {
		super("Cannot find ArticuloFecha " + articuloId + " - " + fecha);
	}
}
