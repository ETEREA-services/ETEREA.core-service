/**
 * 
 */
package eterea.api.rest.model.dto;

import java.io.Serializable;
import java.util.List;

import eterea.api.rest.model.ArticuloMovimiento;
import eterea.api.rest.model.Cliente;
import eterea.api.rest.model.ClienteMovimiento;
import eterea.api.rest.model.Comprobante;
import eterea.api.rest.model.ConceptoFacturado;
import eterea.api.rest.model.Parametro;
import eterea.api.rest.model.PuntoVenta;
import eterea.api.rest.model.RegistroFiscal;
import eterea.api.rest.model.ValorMovimiento;
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
	private Cliente cliente;
	private Comprobante comprobante;
	private Parametro parametro;
	private PuntoVenta puntoVenta;
	private Long nextNumeroFactura;
	private List<ArticuloMovimiento> articuloMovimientos;
	private List<ValorMovimiento> valorMovimientos;
	private List<ConceptoFacturado> conceptoFacturados;
	private List<RegistroFiscal> registroFiscals;
	private Long clienteMovimientoIdAsociado;
	private ClienteMovimiento clienteMovimientoAsociado;

}
