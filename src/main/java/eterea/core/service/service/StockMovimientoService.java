package eterea.core.service.service;

import eterea.core.service.kotlin.exception.StockMovimientoException;
import eterea.core.service.kotlin.model.StockMovimiento;
import eterea.core.service.kotlin.repository.StockMovimientoRepository;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Service;

@Service
public class StockMovimientoService {

    private final StockMovimientoRepository repository;

    public StockMovimientoService(StockMovimientoRepository repository) {
        this.repository = repository;
    }

    public StockMovimiento getLastByComprobanteId(Integer comprobanteId) {
        return repository.findFirstByComprobanteIdOrderByStockMovimientoIdDesc(comprobanteId)
                .orElseThrow(() -> new StockMovimientoException(comprobanteId));
    }

    public StockMovimiento add(StockMovimiento stockMovimiento) {
        return repository.save(stockMovimiento);
    }
}
