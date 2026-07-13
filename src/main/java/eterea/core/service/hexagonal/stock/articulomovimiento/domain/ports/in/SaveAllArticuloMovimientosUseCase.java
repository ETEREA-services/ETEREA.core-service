package eterea.core.service.hexagonal.stock.articulomovimiento.domain.ports.in;

import eterea.core.service.hexagonal.stock.articulomovimiento.domain.model.ArticuloMovimiento;

import java.util.List;

public interface SaveAllArticuloMovimientosUseCase {
    List<ArticuloMovimiento> saveAll(List<ArticuloMovimiento> articuloMovimientos);
}
