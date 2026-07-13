package eterea.core.service.hexagonal.stock.articulomovimiento.domain.ports.in;

import eterea.core.service.hexagonal.stock.articulomovimiento.domain.model.ArticuloMovimiento;

import java.util.List;

public interface AutoCompleteTotalesByClienteMovimientoIdUseCase {

    List<ArticuloMovimiento> calculateTotalesByClienteMovimientoId(Long clienteMovimientoId);

}
