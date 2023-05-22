/**
 * 
 */
package eterea.core.api.rest.model.view.pk;

import java.io.Serializable;
import java.time.OffsetDateTime;

import lombok.Data;

/**
 * @author daniel
 *
 */
@Data
public class ProductoCantidadPk implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4582660810554092196L;
	
	private OffsetDateTime fechaServicio;
	private Integer productoId;

}
