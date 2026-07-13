package eterea.core.service.hexagonal.contable.cuentamovimiento.application.usecases;

import eterea.core.service.hexagonal.contable.cuentamovimiento.domain.model.CuentaMovimiento;
import eterea.core.service.hexagonal.contable.cuentamovimiento.domain.ports.in.SaveCuentaMovimientosUseCase;
import eterea.core.service.hexagonal.contable.cuentamovimiento.domain.ports.out.CuentaMovimientoRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SaveCuentaMovimientosUseCaseImpl implements SaveCuentaMovimientosUseCase {

    private final CuentaMovimientoRepository repository;

    public SaveCuentaMovimientosUseCaseImpl(CuentaMovimientoRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<CuentaMovimiento> saveAll(List<CuentaMovimiento> cuentaMovimientos) {
        return repository.saveAll(cuentaMovimientos);
    }
}
