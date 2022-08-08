/**
 * 
 */
package eterea.api.rest.exception;

/**
 * @author daniel
 *
 */
public class GrupoProductoNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7183478533752360467L;

	public GrupoProductoNotFoundException(Integer grupoId, Integer productoId) {
		super("Cannot find GrupoProducto " + grupoId + "/" + productoId);
	}

}
