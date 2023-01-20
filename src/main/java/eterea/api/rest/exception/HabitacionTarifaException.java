/**
 * 
 */
package eterea.api.rest.exception;

import java.text.MessageFormat;

/**
 * @author daniel
 *
 */
public class HabitacionTarifaException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3769973213753671418L;

	public HabitacionTarifaException(Integer numero, Integer paxs) {
		super(MessageFormat.format("Cannot find HabitacionTarifa {0}/{1}", numero, paxs));
	}

	public HabitacionTarifaException(Long habitacionTarifaId) {
		super(MessageFormat.format("Cannot find HabitacionTarifa {0}", habitacionTarifaId));
	}

}
