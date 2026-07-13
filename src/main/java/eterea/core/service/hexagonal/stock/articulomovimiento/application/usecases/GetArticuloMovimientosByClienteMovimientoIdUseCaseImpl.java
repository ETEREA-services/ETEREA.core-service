package eterea.core.service.hexagonal.stock.articulomovimiento.application.usecases;

import eterea.core.service.hexagonal.stock.articulomovimiento.domain.model.ArticuloMovimiento;
import eterea.core.service.hexagonal.stock.articulomovimiento.domain.ports.in.GetArticuloMovimientosByClienteMovimientoIdUseCase;
import eterea.core.service.hexagonal.stock.articulomovimiento.domain.ports.out.ArticuloMovimientoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetArticuloMovimientosByClienteMovimientoIdUseCaseImpl implements GetArticuloMovimientosByClienteMovimientoIdUseCase {
    private final ArticuloMovimientoRepository repository;

    @Override
    public List<ArticuloMovimiento> findAllByClienteMovimientoId(Long clienteMovimientoId) {
        return repository.findAllByClienteMovimientoId(clienteMovimientoId);
    }
}
