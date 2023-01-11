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

	public ArticuloNotFoundException(String articuloId) {
		super("Cannot find Articulo '" + articuloId + "'");
	}

	public ArticuloNotFoundException(Long uniqueId) {
		super("Cannot find Articulo (uniqueId) '" + uniqueId + "'");
	}

}
