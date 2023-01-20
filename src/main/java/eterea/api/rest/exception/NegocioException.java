/**
 * 
 */
package eterea.api.rest.exception;

import java.text.MessageFormat;

/**
 * @author daniel
 *
 */
public class NegocioException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7152015942177039943L;

	public NegocioException(Integer negocioId) {
		super(MessageFormat.format("Cannot find Negocio {0}", negocioId));
	}

}
