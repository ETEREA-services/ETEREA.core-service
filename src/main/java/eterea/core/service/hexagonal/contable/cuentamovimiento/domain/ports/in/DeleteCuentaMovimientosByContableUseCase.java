package eterea.core.service.hexagonal.contable.cuentamovimiento.domain.ports.in;

import java.time.OffsetDateTime;

public interface DeleteCuentaMovimientosByContableUseCase {
    void deleteAllByContable(OffsetDateTime fechaContable, Integer ordenContable);
}
