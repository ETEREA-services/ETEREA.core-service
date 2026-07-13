package eterea.core.service.hexagonal.contable.cuentamovimiento.domain.ports.in;

import eterea.core.service.hexagonal.contable.cuentamovimiento.domain.model.CuentaMovimiento;

public interface FindCuentaMovimientoByIdUseCase {
    CuentaMovimiento findByCuentaMovimientoId(Long cuentaMovimientoId);
}
