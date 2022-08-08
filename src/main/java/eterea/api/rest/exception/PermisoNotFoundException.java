/**
 * 
 */
package eterea.api.rest.exception;

import java.text.MessageFormat;

/**
 * @author daniel
 *
 */
public class PermisoNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8438536184448083657L;

	public PermisoNotFoundException(String usuario, String opcion) {
		super(MessageFormat.format("Cannot find Permiso (permiso) {0}/{1}", usuario, opcion));
	}

}
