/**
 * 
 */
package eterea.api.rest.exception;

import java.text.MessageFormat;

/**
 * @author daniel
 *
 */
public class VoucherNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7600661609418734304L;

	public VoucherNotFoundException(Long valueId, String string) {
		super(MessageFormat.format("Cannot find Voucher ({0}) -> {1} ", string, valueId));
	}

	public VoucherNotFoundException(String fechaServicio) {
		super("Cannot find Voucher (DateIn) " + fechaServicio);
	}

}
