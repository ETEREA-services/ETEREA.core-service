package eterea.core.service.hexagonal.contable.cuentamovimiento.application.usecases;

import eterea.core.service.hexagonal.contable.cuentamovimiento.domain.ports.in.DeleteCuentaMovimientosByContableUseCase;
import eterea.core.service.hexagonal.contable.cuentamovimiento.domain.ports.out.CuentaMovimientoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;

@Component
@Slf4j
public class DeleteCuentaMovimientosByContableUseCaseImpl implements DeleteCuentaMovimientosByContableUseCase {

    private final CuentaMovimientoRepository repository;

    public DeleteCuentaMovimientosByContableUseCaseImpl(CuentaMovimientoRepository repository) {
        this.repository = repository;
    }

    @Override
    public void deleteAllByContable(OffsetDateTime fechaContable, Integer ordenContable) {
        log.debug("Deleting movimientos contables para fecha={} orden={}", fechaContable, ordenContable);
        repository.deleteAllByFechaAndOrden(fechaContable, ordenContable);
    }
}
