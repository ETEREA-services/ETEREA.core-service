/**
 * 
 */
package eterea.core.api.rest.exception;

/**
 * @author daniel
 *
 */
public class LegajoRegistroException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3205209209385641288L;

	public LegajoRegistroException(Integer legajoId) {
		super("Cannot find LegajoRegistro " + legajoId);
	}
}
