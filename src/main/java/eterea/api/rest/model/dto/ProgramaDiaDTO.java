/**
 * 
 */
package eterea.api.rest.model.dto;

import java.io.Serializable;
import java.util.List;

import eterea.api.rest.model.ClienteMovimiento;
import eterea.api.rest.model.ReservaOrigen;
import eterea.api.rest.model.Voucher;
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
public class ProgramaDiaDTO implements Serializable {

	private static final long serialVersionUID = 1559102796812991435L;
	
	private List<Voucher> vouchers;
	private List<ReservaOrigen> reservaOrigens;
	private List<ClienteMovimiento> clienteMovimientos;

}
