package eterea.core.service.hexagonal.ventas.clientemovimiento.domain.ports.in;

import eterea.core.service.hexagonal.ventas.clientemovimiento.domain.model.ClienteMovimiento;

import java.util.List;

public interface FindClienteMovimientosAsociablesUseCase {
    List<ClienteMovimiento> findTop200Asociables(Long clienteId, Integer comprobanteId);
}
