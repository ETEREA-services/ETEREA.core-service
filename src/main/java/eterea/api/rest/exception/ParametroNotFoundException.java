/**
 * 
 */
package eterea.api.rest.exception;

/**
 * @author daniel
 *
 */
public class ParametroNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 4121543578317798576L;
	
	public ParametroNotFoundException() {
		super("Cannot find Parametro");
	}

}
