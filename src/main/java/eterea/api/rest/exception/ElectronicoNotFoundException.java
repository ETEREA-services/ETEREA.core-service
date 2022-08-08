/**
 * 
 */
package eterea.api.rest.exception;

/**
 * @author daniel
 *
 */
public class ElectronicoNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1525481608424198653L;

	public ElectronicoNotFoundException(Integer comprobanteId, Integer puntoVenta, Long numeroComprobante) {
		super("Cannot find Electronico " + comprobanteId + "/" + puntoVenta + "/" + numeroComprobante);
	}

}
