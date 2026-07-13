package eterea.core.service.hexagonal.stock.stockmovimiento.domain.ports.in;

import eterea.core.service.hexagonal.stock.stockmovimiento.domain.model.StockMovimiento;

public interface FindLastStockMovimientoByComprobanteIdUseCase {
    StockMovimiento findLastByComprobanteId(Integer comprobanteId);
}
