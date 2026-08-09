package eterea.core.service.hexagonal.tesoreria.valormovimiento.application.usecases;

import eterea.core.service.hexagonal.tesoreria.valormovimiento.domain.model.ValorMovimiento;
import eterea.core.service.hexagonal.tesoreria.valormovimiento.domain.ports.in.CreateValorMovimientoUseCase;
import eterea.core.service.hexagonal.tesoreria.valormovimiento.domain.ports.out.ValorMovimientoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateValorMovimientoUseCaseImpl implements CreateValorMovimientoUseCase {

    private final ValorMovimientoRepository repository;

    @Override
    public ValorMovimiento create(ValorMovimiento valorMovimiento) {
        return repository.save(valorMovimiento);
    }
}
