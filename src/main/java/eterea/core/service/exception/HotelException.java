/**
 * 
 */
package eterea.core.service.exception;

/**
 * @author daniel
 *
 */
public class HotelException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4522058399215532152L;

	public HotelException(Integer hotelId) {
		super("Cannot find Hotel " + hotelId);
	}
}
