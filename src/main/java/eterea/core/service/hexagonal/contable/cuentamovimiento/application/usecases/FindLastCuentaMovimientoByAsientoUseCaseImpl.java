package eterea.core.service.hexagonal.contable.cuentamovimiento.application.usecases;

import eterea.core.service.hexagonal.contable.cuentamovimiento.application.exception.CuentaMovimientoException;
import eterea.core.service.hexagonal.contable.cuentamovimiento.domain.model.CuentaMovimiento;
import eterea.core.service.hexagonal.contable.cuentamovimiento.domain.ports.in.FindLastCuentaMovimientoByAsientoUseCase;
import eterea.core.service.hexagonal.contable.cuentamovimiento.domain.ports.out.CuentaMovimientoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;

@Component
@Slf4j
public class FindLastCuentaMovimientoByAsientoUseCaseImpl implements FindLastCuentaMovimientoByAsientoUseCase {

    private final CuentaMovimientoRepository repository;

    public FindLastCuentaMovimientoByAsientoUseCaseImpl(CuentaMovimientoRepository repository) {
        this.repository = repository;
    }

    @Override
    public CuentaMovimiento findLastByAsiento(OffsetDateTime fecha, Integer orden) {
        log.debug("Processing FindLastCuentaMovimientoByAsientoUseCaseImpl.findLastByAsiento");
        return repository.findFirstByFechaAndOrdenOrderByItemDesc(fecha, orden)
                .orElseThrow(() -> new CuentaMovimientoException(fecha, orden));
    }
}
