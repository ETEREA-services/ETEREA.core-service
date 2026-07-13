package eterea.core.service.hexagonal.tesoreria.valormovimiento.domain.ports.in;

import eterea.core.service.hexagonal.tesoreria.valormovimiento.domain.model.ValorMovimiento;

import java.util.List;

public interface SaveAllValorMovimientosUseCase {
    List<ValorMovimiento> saveAll(List<ValorMovimiento> valorMovimientos);
}
