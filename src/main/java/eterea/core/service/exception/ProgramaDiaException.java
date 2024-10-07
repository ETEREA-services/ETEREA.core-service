/**
 * 
 */
package eterea.core.service.exception;

/**
 * @author daniel
 *
 */
public class ProgramaDiaException extends RuntimeException {

	private static final long serialVersionUID = -675856098155434125L;

	public ProgramaDiaException(Long voucherId) {
		super("Cannot find Voucher " + voucherId);
	}

}
