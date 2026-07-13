package eterea.core.service.hexagonal.ventas.clientemovimiento.domain.ports.in;

import eterea.core.service.hexagonal.ventas.clientemovimiento.domain.model.ClienteMovimiento;

public interface UpdateClienteMovimientoUseCase {
    ClienteMovimiento update(ClienteMovimiento clienteMovimiento, Long clienteMovimientoId);
}
