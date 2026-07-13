package eterea.core.service.hexagonal.contable.cuentamovimiento.domain.ports.in;

import eterea.core.service.hexagonal.contable.cuentamovimiento.domain.model.CuentaMovimiento;

import java.time.OffsetDateTime;
import java.util.List;

public interface FindCuentaMovimientosByFechaRangeUseCase {
    List<CuentaMovimiento> findAllByFechaBetween(OffsetDateTime fechaDesde, OffsetDateTime fechaHasta);
}
