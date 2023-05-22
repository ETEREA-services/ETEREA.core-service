/**
 * 
 */
package eterea.core.api.rest.exception;

import java.text.MessageFormat;

/**
 * @author daniel
 *
 */
public class ClienteMovimientoPrevioException extends RuntimeException {

	private static final long serialVersionUID = -3283075965031968092L;

	public ClienteMovimientoPrevioException(Long clienteMovimientoPrevioId) {
		super(MessageFormat.format("Cannot find ClienteMovimientoPrevio id -> {0}", clienteMovimientoPrevioId));
	}

}
