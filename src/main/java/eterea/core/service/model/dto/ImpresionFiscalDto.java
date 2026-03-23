/**
 * 
 */
package eterea.core.service.model.dto;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

import eterea.core.service.kotlin.model.*;
import eterea.core.service.model.ArticuloMovimiento;
import eterea.core.service.model.ClienteMovimiento;
import eterea.core.service.tool.Jsonifier;
import lombok.*;

/**
 * @author daniel
 *
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImpresionFiscalDto implements Serializable {

	@Serial
    private static final long serialVersionUID = 8474478112848630563L;

	private Long numeroFactura;
	private Cliente cliente;
	private Comprobante comprobante;
	private List<ArticuloMovimientoTemporal> articuloMovimientoTemporals;
	private ClienteMovimiento comprobanteOrigen;
	private ClienteMovimientoPrevio clienteMovimientoPrevio;
	private StockMovimiento stockMovimiento;
	private List<ArticuloMovimiento> articuloMovimientos;

	public String jsonify() {
        return Jsonifier.builder(this).build();
	}

}
