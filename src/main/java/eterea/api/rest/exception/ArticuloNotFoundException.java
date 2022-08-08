/**
 * 
 */
package eterea.api.rest.exception;

/**
 * @author daniel
 *
 */
public class ArticuloNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 998757079189055072L;

	public ArticuloNotFoundException(String articuloID) {
		super("Cannot find Articulo '" + articuloID + "'");
	}

	public ArticuloNotFoundException(Long autonumerico) {
		super("Cannot find Articulo " + autonumerico);
	}
}
