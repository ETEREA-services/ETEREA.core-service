package eterea.core.service.hexagonal.stock.articulomovimiento.domain.ports.in;

import eterea.core.service.hexagonal.stock.articulomovimiento.domain.model.ArticuloMovimiento;

import java.util.List;

public interface GetArticuloMovimientosByClienteMovimientoIdUseCase {
    List<ArticuloMovimiento> findAllByClienteMovimientoId(Long clienteMovimientoId);
}
