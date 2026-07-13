package eterea.core.service.hexagonal.stock.articulomovimiento.domain.ports.in;

import eterea.core.service.hexagonal.stock.articulomovimiento.domain.model.ArticuloMovimiento;

public interface GetArticuloMovimientoByIdUseCase {
    ArticuloMovimiento findByArticuloMovimientoId(Long articuloMovimientoId);
}
