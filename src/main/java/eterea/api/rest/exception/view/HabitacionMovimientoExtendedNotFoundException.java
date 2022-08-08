/**
 * 
 */
package eterea.api.rest.exception.view;

import java.text.MessageFormat;

/**
 * @author daniel
 *
 */
public class HabitacionMovimientoExtendedNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 274031697528313147L;

	public HabitacionMovimientoExtendedNotFoundException(Long numeroReserva) {
		super(MessageFormat.format("Cannot find HabitacionMovimiento {0}", numeroReserva));
	}

}
