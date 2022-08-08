/**
 * 
 */
package eterea.api.rest.exception;

/**
 * @author daniel
 *
 */
public class ProductoNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2959243202450714238L;

	public ProductoNotFoundException(Integer productoId) {
		super("Cannot find Producto " + productoId);
	}

}
