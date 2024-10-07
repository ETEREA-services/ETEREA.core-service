/**
 * 
 */
package eterea.core.service.exception;

/**
 * @author daniel
 *
 */
public class GrupoProductoException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7183478533752360467L;

	public GrupoProductoException(Integer grupoId, Integer productoId) {
		super("Cannot find GrupoProducto " + grupoId + "/" + productoId);
	}

}
