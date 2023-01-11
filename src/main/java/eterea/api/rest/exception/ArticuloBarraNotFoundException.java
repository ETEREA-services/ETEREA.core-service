/**
 * 
 */
package eterea.api.rest.exception;

/**
 * @author daniel
 *
 */
public class ArticuloBarraNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1554879359811055359L;

	public ArticuloBarraNotFoundException(String codigoBarras) {
		super("Cannot find ArticuloBarra " + codigoBarras);
	}

}
