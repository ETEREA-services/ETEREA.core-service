package eterea.core.service.hexagonal.tesoreria.valormovimiento.domain.ports.in;

import eterea.core.service.hexagonal.tesoreria.valormovimiento.domain.model.ValorMovimiento;

import java.time.OffsetDateTime;
import java.util.List;

public interface FindValorMovimientosByContableUseCase {
    List<ValorMovimiento> findByContable(OffsetDateTime fechaContable, Integer ordenContable);
}
