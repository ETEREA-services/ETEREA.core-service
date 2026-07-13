package eterea.core.service.hexagonal.stock.stockmovimiento.application.service;

import eterea.core.service.hexagonal.stock.stockmovimiento.application.exception.StockMovimientoException;
import eterea.core.service.hexagonal.stock.stockmovimiento.domain.model.StockMovimiento;
import eterea.core.service.hexagonal.stock.stockmovimiento.domain.ports.in.CreateStockMovimientoUseCase;
import eterea.core.service.hexagonal.stock.stockmovimiento.domain.ports.in.FindLastStockMovimientoByComprobanteIdUseCase;
import eterea.core.service.hexagonal.stock.stockmovimiento.domain.ports.in.FindStockMovimientoByIdUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class StockMovimientoService {

    private final FindStockMovimientoByIdUseCase findStockMovimientoByIdUseCase;
    private final FindLastStockMovimientoByComprobanteIdUseCase findLastStockMovimientoByComprobanteIdUseCase;
    private final CreateStockMovimientoUseCase createStockMovimientoUseCase;

    public StockMovimientoService(FindStockMovimientoByIdUseCase findStockMovimientoByIdUseCase,
                                  FindLastStockMovimientoByComprobanteIdUseCase findLastStockMovimientoByComprobanteIdUseCase,
                                  CreateStockMovimientoUseCase createStockMovimientoUseCase) {
        this.findStockMovimientoByIdUseCase = findStockMovimientoByIdUseCase;
        this.findLastStockMovimientoByComprobanteIdUseCase = findLastStockMovimientoByComprobanteIdUseCase;
        this.createStockMovimientoUseCase = createStockMovimientoUseCase;
    }

    public StockMovimiento findByStockMovimientoId(Long stockMovimientoId) {
        return findStockMovimientoByIdUseCase.findByStockMovimientoId(stockMovimientoId);
    }

    public StockMovimiento getLastByComprobanteId(Integer comprobanteId) {
        return findLastStockMovimientoByComprobanteIdUseCase.findLastByComprobanteId(comprobanteId);
    }

    public StockMovimiento add(StockMovimiento stockMovimiento) {
        return createStockMovimientoUseCase.create(stockMovimiento);
    }

}
