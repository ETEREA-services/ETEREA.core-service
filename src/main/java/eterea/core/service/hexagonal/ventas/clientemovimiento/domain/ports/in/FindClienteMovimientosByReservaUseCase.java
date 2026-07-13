package eterea.core.service.hexagonal.ventas.clientemovimiento.domain.ports.in;

import eterea.core.service.hexagonal.ventas.clientemovimiento.domain.model.ClienteMovimiento;

import java.util.List;

public interface FindClienteMovimientosByReservaUseCase {
    List<ClienteMovimiento> findAllByReservaIds(List<Long> reservaIds);
    List<ClienteMovimiento> findAllByReservaId(Long reservaId);
}
