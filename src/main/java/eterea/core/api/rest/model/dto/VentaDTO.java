/**
 * 
 */
package eterea.core.api.rest.model.dto;

import java.io.Serializable;

import eterea.core.api.rest.model.ClienteMovimiento;
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
public class VentaDTO implements Serializable {

	private static final long serialVersionUID = 4518134393180168637L;
	
	private ClienteMovimiento clienteMovimiento;

}
