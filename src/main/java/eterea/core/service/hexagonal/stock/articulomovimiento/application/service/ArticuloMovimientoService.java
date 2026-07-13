package eterea.core.service.hexagonal.stock.articulomovimiento.application.service;

import eterea.core.service.hexagonal.stock.articulomovimiento.domain.model.ArticuloMovimiento;
import eterea.core.service.hexagonal.stock.articulomovimiento.domain.ports.in.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ArticuloMovimientoService {

    private final GetArticuloMovimientosByClienteMovimientoIdUseCase getByClienteMovimientoId;
    private final GetArticuloMovimientosByStockMovimientoIdUseCase getByStockMovimientoId;
    private final GetArticuloMovimientoByIdUseCase getById;
    private final CreateArticuloMovimientoUseCase create;
    private final SaveAllArticuloMovimientosUseCase saveAll;
    private final AutoCompleteTotalesByClienteMovimientoIdUseCase autoComplete;

    public List<ArticuloMovimiento> findAllByClienteMovimientoId(Long clienteMovimientoId) {
        log.debug("\n\nProcessing ArticuloMovimientoService.findAllByClienteMovimientoId: {}\n\n", clienteMovimientoId);
        autoCompleteTotalesByClienteMovimientoId(clienteMovimientoId);
        return getByClienteMovimientoId.findAllByClienteMovimientoId(clienteMovimientoId);
    }

    public List<ArticuloMovimiento> findAllByStockMovimientoId(Long stockMovimientoId) {
        return getByStockMovimientoId.findAllByStockMovimientoId(stockMovimientoId);
    }

    public ArticuloMovimiento findByArticuloMovimientoId(Long articuloMovimientoId) {
        return getById.findByArticuloMovimientoId(articuloMovimientoId);
    }

    public ArticuloMovimiento add(ArticuloMovimiento articuloMovimiento) {
        return create.add(articuloMovimiento);
    }

    public List<ArticuloMovimiento> saveAll(List<ArticuloMovimiento> articuloMovimientos) {
        return saveAll.saveAll(articuloMovimientos);
    }

    public List<ArticuloMovimiento> autoCompleteTotalesByClienteMovimientoId(Long clienteMovimientoId) {
        log.debug("\n\nProcessing ArticuloMovimientoService.autoCompleteTotalesByClienteMovimientoId\n\n");
        return autoComplete.calculateTotalesByClienteMovimientoId(clienteMovimientoId);
    }

}
