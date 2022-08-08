/**
 * 
 */
package eterea.api.rest.exception;

/**
 * @author daniel
 *
 */
public class ClienteGrupoCupoNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8964210851575849023L;

	public ClienteGrupoCupoNotFoundException(Long clienteId, Integer grupoId, Integer dias) {
		super("Cannot find ClienteGrupoCupo " + clienteId + "/" + grupoId + "/" + dias);
	}

	public ClienteGrupoCupoNotFoundException(Long clientegrupocupoId) {
		super("Cannot find ClienteGrupoCupo " + clientegrupocupoId);
	}

}
