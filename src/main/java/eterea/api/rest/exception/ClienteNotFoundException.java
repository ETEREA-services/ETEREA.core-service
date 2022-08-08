/**
 * 
 */
package eterea.api.rest.exception;

import java.text.MessageFormat;

/**
 * @author daniel
 *
 */
public class ClienteNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3569157004260674955L;

	public ClienteNotFoundException(Long clienteId) {
		super(MessageFormat.format("Cannot find Cliente {0}", clienteId));
	}

	public ClienteNotFoundException(String numeroDocumento) {
		super(MessageFormat.format("Cannot find Cliente {0}", numeroDocumento));
	}

	public ClienteNotFoundException() {
		super("Cannot find Cliente");
	}
}
