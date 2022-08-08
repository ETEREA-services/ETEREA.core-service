/**
 * 
 */
package eterea.api.rest.exception;

import java.text.MessageFormat;

/**
 * @author daniel
 *
 */
public class HabitacionNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3854243777561959702L;

	public HabitacionNotFoundException(Integer numero) {
		super(MessageFormat.format("Cannot find Habitacion {}", numero));
	}

}
