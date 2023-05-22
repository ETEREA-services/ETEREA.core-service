/**
 * 
 */
package eterea.core.api.rest.exception;

/**
 * @author daniel
 *
 */
public class ArticuloException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 998757079189055072L;

	public ArticuloException(String articuloID) {
		super("Cannot find Articulo '" + articuloID + "'");
	}

	public ArticuloException(Long autonumerico) {
		super("Cannot find Articulo " + autonumerico);
	}
}
