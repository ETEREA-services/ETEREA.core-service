package eterea.core.service.hexagonal.stock.stockmovimiento.application.usecases;

import eterea.core.service.hexagonal.stock.stockmovimiento.domain.model.StockMovimiento;
import eterea.core.service.hexagonal.stock.stockmovimiento.domain.ports.in.CreateStockMovimientoUseCase;
import eterea.core.service.hexagonal.stock.stockmovimiento.domain.ports.out.StockMovimientoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CreateStockMovimientoUseCaseImpl implements CreateStockMovimientoUseCase {

    private final StockMovimientoRepository repository;

    public CreateStockMovimientoUseCaseImpl(StockMovimientoRepository repository) {
        this.repository = repository;
    }

    @Override
    public StockMovimiento create(StockMovimiento stockMovimiento) {
        log.debug("Processing CreateStockMovimientoUseCaseImpl.create");
        return repository.save(stockMovimiento);
    }
}
