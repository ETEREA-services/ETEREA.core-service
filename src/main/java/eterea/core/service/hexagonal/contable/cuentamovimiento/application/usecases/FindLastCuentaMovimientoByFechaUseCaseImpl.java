package eterea.core.service.hexagonal.contable.cuentamovimiento.application.usecases;

import eterea.core.service.hexagonal.contable.cuentamovimiento.application.exception.CuentaMovimientoException;
import eterea.core.service.hexagonal.contable.cuentamovimiento.domain.model.CuentaMovimiento;
import eterea.core.service.hexagonal.contable.cuentamovimiento.domain.ports.in.FindLastCuentaMovimientoByFechaUseCase;
import eterea.core.service.hexagonal.contable.cuentamovimiento.domain.ports.out.CuentaMovimientoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;

@Component
@Slf4j
@RequiredArgsConstructor
public class FindLastCuentaMovimientoByFechaUseCaseImpl implements FindLastCuentaMovimientoByFechaUseCase {

    private final CuentaMovimientoRepository repository;

    @Override
    public CuentaMovimiento findLastByFecha(OffsetDateTime fecha) {
        log.debug("Processing FindLastCuentaMovimientoByFechaUseCaseImpl.findLastByFecha");
        return repository.findFirstByFechaOrderByOrdenDesc(fecha)
                .orElseThrow(() -> new CuentaMovimientoException(fecha));
    }
}
