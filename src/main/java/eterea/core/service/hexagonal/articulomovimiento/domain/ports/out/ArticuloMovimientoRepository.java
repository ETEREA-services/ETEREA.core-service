package eterea.core.service.hexagonal.articulomovimiento.domain.ports.out;

import eterea.core.service.hexagonal.articulomovimiento.domain.model.ArticuloMovimiento;

import java.util.List;
import java.util.Optional;

public interface ArticuloMovimientoRepository {
    List<ArticuloMovimiento> findAllByClienteMovimientoId(Long clienteMovimientoId);
    List<ArticuloMovimiento> findAllByStockMovimientoId(Long stockMovimientoId);
    Optional<ArticuloMovimiento> findByArticuloMovimientoId(Long articuloMovimientoId);
    ArticuloMovimiento save(ArticuloMovimiento articuloMovimiento);
    List<ArticuloMovimiento> saveAll(List<ArticuloMovimiento> articuloMovimientos);
}
