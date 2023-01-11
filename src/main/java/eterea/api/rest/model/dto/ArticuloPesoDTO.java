/**
 * 
 */
package eterea.api.rest.model.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import eterea.api.rest.model.Articulo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author daniel
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticuloPesoDTO implements Serializable {

	private static final long serialVersionUID = 8591632976781251909L;
	
	private Articulo articulo;
	private BigDecimal peso = BigDecimal.ZERO;

}
