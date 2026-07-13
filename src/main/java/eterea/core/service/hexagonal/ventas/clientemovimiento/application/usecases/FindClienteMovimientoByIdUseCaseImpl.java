package eterea.core.service.hexagonal.ventas.clientemovimiento.application.usecases;

import eterea.core.service.hexagonal.ventas.clientemovimiento.application.exception.ClienteMovimientoException;
import eterea.core.service.hexagonal.ventas.clientemovimiento.domain.model.ClienteMovimiento;
import eterea.core.service.hexagonal.ventas.clientemovimiento.domain.ports.in.FindClienteMovimientoByIdUseCase;
import eterea.core.service.hexagonal.ventas.clientemovimiento.domain.ports.out.ClienteMovimientoRepository;
import org.springframework.stereotype.Component;

@Component
public class FindClienteMovimientoByIdUseCaseImpl implements FindClienteMovimientoByIdUseCase {

    private final ClienteMovimientoRepository repository;

    public FindClienteMovimientoByIdUseCaseImpl(ClienteMovimientoRepository repository) {
        this.repository = repository;
    }

    @Override
    public ClienteMovimiento findByClienteMovimientoId(Long clienteMovimientoId) {
        return repository.findByClienteMovimientoId(clienteMovimientoId)
                .orElseThrow(() -> new ClienteMovimientoException(clienteMovimientoId));
    }
}
