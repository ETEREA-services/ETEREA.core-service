package eterea.core.service.hexagonal.tesoreria.valormovimiento.application.usecases;

import eterea.core.service.hexagonal.tesoreria.valormovimiento.domain.model.ValorMovimiento;
import eterea.core.service.hexagonal.tesoreria.valormovimiento.domain.ports.in.SaveAllValorMovimientosUseCase;
import eterea.core.service.hexagonal.tesoreria.valormovimiento.domain.ports.out.ValorMovimientoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SaveAllValorMovimientosUseCaseImpl implements SaveAllValorMovimientosUseCase {

    private final ValorMovimientoRepository repository;

    @Override
    public List<ValorMovimiento> saveAll(List<ValorMovimiento> valorMovimientos) {
        return repository.saveAll(valorMovimientos);
    }
}
