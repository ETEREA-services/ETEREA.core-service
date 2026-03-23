package eterea.core.service.service;

import eterea.core.service.exception.StockMovimientoException;
import eterea.core.service.kotlin.model.StockMovimiento;
import eterea.core.service.kotlin.repository.StockMovimientoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class StockMovimientoService {

    private final StockMovimientoRepository repository;

    public StockMovimiento findByStockMovimientoId(Long stockMovimientoId) {
        return Objects.requireNonNull(repository.findByStockMovimientoId(stockMovimientoId)).orElseThrow(() -> new StockMovimientoException(stockMovimientoId));
    }

    public StockMovimiento getLastByComprobanteId(Integer comprobanteId) {
        return Objects.requireNonNull(repository.findFirstByComprobanteIdOrderByStockMovimientoIdDesc(comprobanteId))
                .orElseThrow(() -> new StockMovimientoException(comprobanteId));
    }

    public StockMovimiento add(StockMovimiento stockMovimiento) {
        return repository.save(stockMovimiento);
    }

}
