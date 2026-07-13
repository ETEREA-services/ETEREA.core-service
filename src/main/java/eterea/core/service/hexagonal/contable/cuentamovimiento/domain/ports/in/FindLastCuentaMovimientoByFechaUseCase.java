package eterea.core.service.hexagonal.contable.cuentamovimiento.domain.ports.in;

import eterea.core.service.hexagonal.contable.cuentamovimiento.domain.model.CuentaMovimiento;

import java.time.OffsetDateTime;

public interface FindLastCuentaMovimientoByFechaUseCase {
    CuentaMovimiento findLastByFecha(OffsetDateTime fecha);
}
