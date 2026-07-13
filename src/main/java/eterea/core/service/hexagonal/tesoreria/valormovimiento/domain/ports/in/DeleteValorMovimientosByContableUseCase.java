package eterea.core.service.hexagonal.tesoreria.valormovimiento.domain.ports.in;

import java.time.OffsetDateTime;

public interface DeleteValorMovimientosByContableUseCase {
    void deleteByContable(OffsetDateTime fechaContable, Integer ordenContable);
}
