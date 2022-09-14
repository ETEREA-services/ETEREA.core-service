/**
 * 
 */
package eterea.api.rest.exception;

/**
 * @author daniel
 *
 */
public class ProgramaDiaNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -675856098155434125L;

	public ProgramaDiaNotFoundException(Long voucherId) {
		super("Cannot find Voucher " + voucherId);
	}

}
