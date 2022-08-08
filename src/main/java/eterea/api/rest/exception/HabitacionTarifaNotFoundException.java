/**
 * 
 */
package eterea.api.rest.exception;

import java.text.MessageFormat;

/**
 * @author daniel
 *
 */
public class HabitacionTarifaNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3769973213753671418L;

	public HabitacionTarifaNotFoundException(Integer numero, Integer paxs) {
		super(MessageFormat.format("Cannot find HabitacionTarifa {0}/{1}", numero, paxs));
	}

	public HabitacionTarifaNotFoundException(Long habitacionTarifaId) {
		super(MessageFormat.format("Cannot find HabitacionTarifa {0}", habitacionTarifaId));
	}

}
