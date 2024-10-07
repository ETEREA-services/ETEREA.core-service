/**
 * 
 */
package eterea.core.service.model.dto;

import java.io.Serializable;

import eterea.core.service.kotlin.model.ClienteMovimiento;
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
public class VentaDto implements Serializable {

	private static final long serialVersionUID = 4518134393180168637L;
	
	private ClienteMovimiento clienteMovimiento;

}
