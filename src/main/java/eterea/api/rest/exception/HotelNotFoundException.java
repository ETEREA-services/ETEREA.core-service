/**
 * 
 */
package eterea.api.rest.exception;

/**
 * @author daniel
 *
 */
public class HotelNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4522058399215532152L;

	public HotelNotFoundException(Integer hotelId) {
		super("Cannot find Hotel " + hotelId);
	}
}
