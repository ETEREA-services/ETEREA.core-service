/**
 * 
 */
package eterea.core.service.exception;

/**
 * @author daniel
 *
 */
public class ProductoException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2959243202450714238L;

	public ProductoException(Integer productoId) {
		super("Cannot find Producto " + productoId);
	}

}
