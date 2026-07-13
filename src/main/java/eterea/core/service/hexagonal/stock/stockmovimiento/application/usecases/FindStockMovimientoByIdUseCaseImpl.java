package eterea.core.service.hexagonal.stock.stockmovimiento.application.usecases;

import eterea.core.service.hexagonal.stock.stockmovimiento.application.exception.StockMovimientoException;
import eterea.core.service.hexagonal.stock.stockmovimiento.domain.model.StockMovimiento;
import eterea.core.service.hexagonal.stock.stockmovimiento.domain.ports.in.FindStockMovimientoByIdUseCase;
import eterea.core.service.hexagonal.stock.stockmovimiento.domain.ports.out.StockMovimientoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class FindStockMovimientoByIdUseCaseImpl implements FindStockMovimientoByIdUseCase {

    private final StockMovimientoRepository repository;

    public FindStockMovimientoByIdUseCaseImpl(StockMovimientoRepository repository) {
        this.repository = repository;
    }

    @Override
    public StockMovimiento findByStockMovimientoId(Long stockMovimientoId) {
        log.debug("Processing FindStockMovimientoByIdUseCaseImpl.findByStockMovimientoId");
        return repository.findByStockMovimientoId(stockMovimientoId)
                .orElseThrow(() -> new StockMovimientoException(stockMovimientoId));
    }
}
