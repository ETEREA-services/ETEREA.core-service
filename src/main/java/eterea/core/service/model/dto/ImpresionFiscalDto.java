/**
 * 
 */
package eterea.core.service.model.dto;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

import eterea.core.service.hexagonal.stock.articulomovimiento.domain.model.ArticuloMovimiento;
import eterea.core.service.hexagonal.stock.stockmovimiento.domain.model.StockMovimiento;
import eterea.core.service.hexagonal.stock.stockmovimiento.infrastructure.persistence.entity.StockMovimientoEntity;
import eterea.core.service.hexagonal.ventas.clientemovimiento.domain.model.ClienteMovimiento;
import eterea.core.service.hexagonal.comprobante.domain.model.Comprobante;
import eterea.core.service.kotlin.model.*;
import eterea.core.service.tool.Jsonifier;
import eterea.core.service.tool.Jsonifyable;
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
public class ImpresionFiscalDto implements Serializable, Jsonifyable {

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

}
