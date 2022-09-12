/**
 * 
 */
package eterea.api.rest.exception;

/**
 * @author daniel
 *
 */
public class ReservaOrigenNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -1604184602464498999L;

	public ReservaOrigenNotFoundException(Integer reservaOrigenId) {
		super("Cannot find ReservaOrigen " + reservaOrigenId);
	}

}
