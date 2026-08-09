package eterea.core.service.hexagonal.contable.cuentamovimiento.application.usecases;

import eterea.core.service.hexagonal.contable.cuentamovimiento.domain.model.CuentaMovimiento;
import eterea.core.service.hexagonal.contable.cuentamovimiento.domain.ports.in.FindCuentaMovimientosByContableUseCase;
import eterea.core.service.hexagonal.contable.cuentamovimiento.domain.ports.out.CuentaMovimientoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class FindCuentaMovimientosByContableUseCaseImpl implements FindCuentaMovimientosByContableUseCase {

    private final CuentaMovimientoRepository repository;

    @Override
    public List<CuentaMovimiento> findAllByContable(OffsetDateTime fechaContable, Integer ordenContable) {
        return repository.findAllByFechaAndOrden(fechaContable, ordenContable);
    }
}
