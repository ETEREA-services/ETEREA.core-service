/**
 * 
 */
package eterea.core.api.rest.model.dto;

import java.io.Serializable;
import java.util.List;

import eterea.core.api.rest.kotlin.model.*;
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
public class ImpresionFiscalDto implements Serializable {

	private static final long serialVersionUID = 8474478112848630563L;

	private Long numeroFactura;
	private Cliente cliente;
	private Comprobante comprobante;
	private List<ArticuloMovimientoTemporal> articuloMovimientoTemporals;
	private ClienteMovimiento comprobanteOrigen;
	private ClienteMovimientoPrevio clienteMovimientoPrevio;

}
