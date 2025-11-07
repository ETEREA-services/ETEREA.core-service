/**
 * 
 */
package eterea.core.service.exception;

import java.time.OffsetDateTime;

/**
 * @author daniel
 *
 */
public class ArticuloFechaException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5015112641151317112L;

	public ArticuloFechaException(Long articulofechaId) {
		super("Cannot find ArticuloFecha " + articulofechaId);
	}

	public ArticuloFechaException(String articuloId, OffsetDateTime fecha) {
		super("Cannot find ArticuloFecha " + articuloId + " - " + fecha);
	}

	public ArticuloFechaException(String articuloId, OffsetDateTime fechaInicio, OffsetDateTime fechaFin) {
		super("Cannot find ArticuloFecha " + articuloId + " for period " + fechaInicio + " - " + fechaFin);
	}
}
