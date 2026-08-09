package eterea.core.service.hexagonal.ventas.clientemovimiento.application.usecases;

import eterea.core.service.hexagonal.ventas.clientemovimiento.domain.model.ClienteMovimiento;
import eterea.core.service.hexagonal.ventas.clientemovimiento.domain.ports.in.CreateClienteMovimientoUseCase;
import eterea.core.service.hexagonal.ventas.clientemovimiento.domain.ports.out.ClienteMovimientoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateClienteMovimientoUseCaseImpl implements CreateClienteMovimientoUseCase {

    private final ClienteMovimientoRepository repository;

    @Override
    public ClienteMovimiento add(ClienteMovimiento clienteMovimiento) {
        return repository.save(clienteMovimiento);
    }
}
