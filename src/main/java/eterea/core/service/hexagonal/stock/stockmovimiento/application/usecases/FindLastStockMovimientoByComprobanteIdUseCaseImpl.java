package eterea.core.service.hexagonal.stock.stockmovimiento.application.usecases;

import eterea.core.service.hexagonal.stock.stockmovimiento.application.exception.StockMovimientoException;
import eterea.core.service.hexagonal.stock.stockmovimiento.domain.model.StockMovimiento;
import eterea.core.service.hexagonal.stock.stockmovimiento.domain.ports.in.FindLastStockMovimientoByComprobanteIdUseCase;
import eterea.core.service.hexagonal.stock.stockmovimiento.domain.ports.out.StockMovimientoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class FindLastStockMovimientoByComprobanteIdUseCaseImpl implements FindLastStockMovimientoByComprobanteIdUseCase {

    private final StockMovimientoRepository repository;

    public FindLastStockMovimientoByComprobanteIdUseCaseImpl(StockMovimientoRepository repository) {
        this.repository = repository;
    }

    @Override
    public StockMovimiento findLastByComprobanteId(Integer comprobanteId) {
        log.debug("Processing FindLastStockMovimientoByComprobanteIdUseCaseImpl.findLastByComprobanteId");
        return repository.findFirstByComprobanteIdOrderByStockMovimientoIdDesc(comprobanteId)
                .orElseThrow(() -> new StockMovimientoException(comprobanteId));
    }
}
