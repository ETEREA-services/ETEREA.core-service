package eterea.core.service.hexagonal.stock.stockmovimiento.domain.ports.out;

import eterea.core.service.hexagonal.stock.stockmovimiento.domain.model.StockMovimiento;

import java.util.Optional;

public interface StockMovimientoRepository {

    Optional<StockMovimiento> findByStockMovimientoId(Long stockMovimientoId);

    Optional<StockMovimiento> findFirstByComprobanteIdOrderByStockMovimientoIdDesc(Integer comprobanteId);

    StockMovimiento save(StockMovimiento stockMovimiento);

}
