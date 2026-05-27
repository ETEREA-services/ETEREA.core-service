package eterea.core.service.hexagonal.articulomovimiento.domain.ports.in;

import eterea.core.service.hexagonal.articulomovimiento.domain.model.ArticuloMovimiento;

public interface CreateArticuloMovimientoUseCase {
    ArticuloMovimiento add(ArticuloMovimiento articuloMovimiento);
}
