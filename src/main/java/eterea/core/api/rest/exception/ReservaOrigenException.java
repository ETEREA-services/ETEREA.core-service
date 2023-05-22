/**
 * 
 */
package eterea.core.api.rest.exception;

/**
 * @author daniel
 *
 */
public class ReservaOrigenException extends RuntimeException {

	private static final long serialVersionUID = -1604184602464498999L;

	public ReservaOrigenException(Integer reservaOrigenId) {
		super("Cannot find ReservaOrigen " + reservaOrigenId);
	}

}
