package eterea.core.service.hexagonal.contable.cuentamovimiento.application.usecases;

import eterea.core.service.hexagonal.contable.cuentamovimiento.application.exception.CuentaMovimientoException;
import eterea.core.service.hexagonal.contable.cuentamovimiento.domain.model.CuentaMovimiento;
import eterea.core.service.hexagonal.contable.cuentamovimiento.domain.ports.in.FindCuentaMovimientoByIdUseCase;
import eterea.core.service.hexagonal.contable.cuentamovimiento.domain.ports.out.CuentaMovimientoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class FindCuentaMovimientoByIdUseCaseImpl implements FindCuentaMovimientoByIdUseCase {

    private final CuentaMovimientoRepository repository;

    @Override
    public CuentaMovimiento findByCuentaMovimientoId(Long cuentaMovimientoId) {
        log.debug("Processing FindCuentaMovimientoByIdUseCaseImpl.findByCuentaMovimientoId");
        return repository.findByCuentaMovimientoId(cuentaMovimientoId)
                .orElseThrow(() -> new CuentaMovimientoException(cuentaMovimientoId));
    }
}
