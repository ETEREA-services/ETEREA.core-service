package eterea.core.service.hexagonal.tesoreria.valormovimiento.application.usecases;

import eterea.core.service.hexagonal.tesoreria.valormovimiento.domain.model.ValorMovimiento;
import eterea.core.service.hexagonal.tesoreria.valormovimiento.domain.ports.in.FindValorMovimientosByContableUseCase;
import eterea.core.service.hexagonal.tesoreria.valormovimiento.domain.ports.out.ValorMovimientoRepository;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.List;

@Component
public class FindValorMovimientosByContableUseCaseImpl implements FindValorMovimientosByContableUseCase {

    private final ValorMovimientoRepository repository;

    public FindValorMovimientosByContableUseCaseImpl(ValorMovimientoRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ValorMovimiento> findByContable(OffsetDateTime fechaContable, Integer ordenContable) {
        return repository.findAllByFechaContableAndOrdenContable(fechaContable, ordenContable);
    }
}
