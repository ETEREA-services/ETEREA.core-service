/**
 * 
 */
package eterea.core.service.model.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import eterea.core.service.kotlin.model.*;
import eterea.core.service.model.ClienteMovimiento;
import eterea.core.service.tool.Jsonifier;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author daniel
 *
 */
@Data
@Builder
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

	public String jsonify() {
        return Jsonifier.builder(this).build();
	}

}
