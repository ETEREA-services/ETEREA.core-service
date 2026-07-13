package eterea.core.service.hexagonal.ventas.clientemovimiento.application.usecases;

import eterea.core.service.hexagonal.ventas.clientemovimiento.domain.model.ClienteMovimiento;
import eterea.core.service.hexagonal.ventas.clientemovimiento.domain.ports.in.FindClienteMovimientosByReservaUseCase;
import eterea.core.service.hexagonal.ventas.clientemovimiento.domain.ports.out.ClienteMovimientoRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FindClienteMovimientosByReservaUseCaseImpl implements FindClienteMovimientosByReservaUseCase {

    private final ClienteMovimientoRepository repository;

    public FindClienteMovimientosByReservaUseCaseImpl(ClienteMovimientoRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ClienteMovimiento> findAllByReservaIds(List<Long> reservaIds) {
        return repository.findAllByReservaIdIn(reservaIds);
    }

    @Override
    public List<ClienteMovimiento> findAllByReservaId(Long reservaId) {
        return repository.findAllByReservaId(reservaId);
    }
}
