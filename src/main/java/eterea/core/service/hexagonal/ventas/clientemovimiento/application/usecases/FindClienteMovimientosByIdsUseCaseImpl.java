package eterea.core.service.hexagonal.ventas.clientemovimiento.application.usecases;

import eterea.core.service.hexagonal.ventas.clientemovimiento.domain.model.ClienteMovimiento;
import eterea.core.service.hexagonal.ventas.clientemovimiento.domain.ports.in.FindClienteMovimientosByIdsUseCase;
import eterea.core.service.hexagonal.ventas.clientemovimiento.domain.ports.out.ClienteMovimientoRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FindClienteMovimientosByIdsUseCaseImpl implements FindClienteMovimientosByIdsUseCase {

    private final ClienteMovimientoRepository repository;

    public FindClienteMovimientosByIdsUseCaseImpl(ClienteMovimientoRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ClienteMovimiento> findAllByIds(List<Long> clienteMovimientoIds) {
        return repository.findAllByClienteMovimientoIdIn(clienteMovimientoIds);
    }
}
