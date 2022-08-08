/**
 * 
 */
package eterea.api.rest.exception;

/**
 * @author daniel
 *
 */
public class LegajoRegistroNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3205209209385641288L;

	public LegajoRegistroNotFoundException(Integer legajoId) {
		super("Cannot find LegajoRegistro " + legajoId);
	}
}
