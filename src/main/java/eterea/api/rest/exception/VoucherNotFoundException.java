/**
 * 
 */
package eterea.api.rest.exception;

/**
 * @author daniel
 *
 */
public class VoucherNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7600661609418734304L;

	public VoucherNotFoundException(Long reservaId) {
		super("Cannot find Voucher (reserva) " + reservaId);
	}

}
