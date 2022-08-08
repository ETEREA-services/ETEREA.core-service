/**
 * 
 */
package eterea.api.rest.exception;

/**
 * @author daniel
 *
 */
public class ComprobanteAfipNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1390156488409610663L;

	public ComprobanteAfipNotFoundException(Integer comprobanteAfipId) {
		super("Cannot find ComprobanteAfip " + comprobanteAfipId);
	}

}
