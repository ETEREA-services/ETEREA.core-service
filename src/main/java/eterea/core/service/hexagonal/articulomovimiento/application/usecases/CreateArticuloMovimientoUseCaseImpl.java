package eterea.core.service.hexagonal.articulomovimiento.application.usecases;

import eterea.core.service.hexagonal.articulomovimiento.domain.model.ArticuloMovimiento;
import eterea.core.service.hexagonal.articulomovimiento.domain.ports.in.CreateArticuloMovimientoUseCase;
import eterea.core.service.hexagonal.articulomovimiento.domain.ports.out.ArticuloMovimientoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateArticuloMovimientoUseCaseImpl implements CreateArticuloMovimientoUseCase {
    private final ArticuloMovimientoRepository repository;

    @Override
    public ArticuloMovimiento add(ArticuloMovimiento articuloMovimiento) {
        return repository.save(articuloMovimiento);
    }
}
