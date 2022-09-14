/**
 * 
 */
package eterea.api.rest.exception;

/**
 * @author daniel
 *
 */
public class ProveedorNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 5119802270092164014L;

	public ProveedorNotFoundException(Integer proveedorId) {
		super("Cannot find Proveedor " + proveedorId);
	}

}
