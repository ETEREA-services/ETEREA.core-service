/**
 * 
 */
package eterea.core.api.rest.exception;

/**
 * @author daniel
 *
 */
public class ComprobanteAfipException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1390156488409610663L;

	public ComprobanteAfipException(Integer comprobanteAfipId) {
		super("Cannot find ComprobanteAfip " + comprobanteAfipId);
	}

}
