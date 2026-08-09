package eterea.core.service.hexagonal.contable.cuentamovimiento.application.usecases;

import eterea.core.service.hexagonal.contable.cuentamovimiento.domain.model.CuentaMovimiento;
import eterea.core.service.hexagonal.contable.cuentamovimiento.domain.ports.in.FindCuentaMovimientosByFechaRangeUseCase;
import eterea.core.service.hexagonal.contable.cuentamovimiento.domain.ports.out.CuentaMovimientoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class FindCuentaMovimientosByFechaRangeUseCaseImpl implements FindCuentaMovimientosByFechaRangeUseCase {

    private final CuentaMovimientoRepository repository;

    @Override
    public List<CuentaMovimiento> findAllByFechaBetween(OffsetDateTime fechaDesde, OffsetDateTime fechaHasta) {
        return repository.findAllByFechaBetween(fechaDesde, fechaHasta);
    }
}
