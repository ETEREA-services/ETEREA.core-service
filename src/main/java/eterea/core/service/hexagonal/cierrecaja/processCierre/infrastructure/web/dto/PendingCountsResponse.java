package eterea.core.service.hexagonal.cierrecaja.processCierre.infrastructure.web.dto;

import eterea.core.service.hexagonal.ventas.clientemovimiento.infrastructure.web.dto.ClienteMovimientoResponse;
import eterea.core.service.hexagonal.contable.cuentamovimiento.infrastructure.web.dto.CuentaMovimientoResponse;
import eterea.core.service.hexagonal.stock.stockmovimiento.infrastructure.web.dto.StockMovimientoResponse;
import eterea.core.service.hexagonal.tesoreria.valormovimiento.infrastructure.web.dto.ValorMovimientoResponse;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PendingCountsResponse {

    private Long countClienteMovimiento;
    private Long countCuentaMovimiento;
    private Long countCuentaMovimientoCuentaCero;
    private Long countValorMovimiento;
    private Long countStockMovimiento;

    private List<ClienteMovimientoResponse> clienteMovimientos;
    private List<CuentaMovimientoResponse> cuentaMovimientos;
    private List<ValorMovimientoResponse> valorMovimientos;
    private List<StockMovimientoResponse> stockMovimientos;

}
