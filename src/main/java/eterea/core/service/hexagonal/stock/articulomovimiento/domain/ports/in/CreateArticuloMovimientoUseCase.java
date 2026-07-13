package eterea.core.service.hexagonal.stock.articulomovimiento.domain.ports.in;

import eterea.core.service.hexagonal.stock.articulomovimiento.domain.model.ArticuloMovimiento;

public interface CreateArticuloMovimientoUseCase {
    ArticuloMovimiento add(ArticuloMovimiento articuloMovimiento);
}
