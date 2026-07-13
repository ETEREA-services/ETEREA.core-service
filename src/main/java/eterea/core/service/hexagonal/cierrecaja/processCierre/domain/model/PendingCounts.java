package eterea.core.service.hexagonal.cierrecaja.processCierre.domain.model;

import eterea.core.service.hexagonal.ventas.clientemovimiento.domain.model.ClienteMovimiento;
import eterea.core.service.hexagonal.contable.cuentamovimiento.domain.model.CuentaMovimiento;
import eterea.core.service.hexagonal.stock.stockmovimiento.domain.model.StockMovimiento;
import eterea.core.service.hexagonal.tesoreria.valormovimiento.domain.model.ValorMovimiento;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PendingCounts {

    private Long countClienteMovimiento;
    private Long countCuentaMovimiento;
    private Long countCuentaMovimientoCuentaCero;
    private Long countValorMovimiento;
    private Long countStockMovimiento;

    private List<ClienteMovimiento> clienteMovimientos;
    private List<CuentaMovimiento> cuentaMovimientos;
    private List<ValorMovimiento> valorMovimientos;
    private List<StockMovimiento> stockMovimientos;

}
