package eterea.core.service.hexagonal.contable.cuentamovimiento.domain.ports.in;

import eterea.core.service.hexagonal.contable.cuentamovimiento.domain.model.CuentaMovimiento;

import java.util.List;

public interface SaveCuentaMovimientosUseCase {
    List<CuentaMovimiento> saveAll(List<CuentaMovimiento> cuentaMovimientos);
}
