package eterea.core.service.hexagonal.contable.cuenta.domain.ports.in;

import eterea.core.service.hexagonal.contable.cuenta.domain.model.Cuenta;

public interface FindCuentaByNumeroCuentaUseCase {
    Cuenta findByNumeroCuenta(Long numeroCuenta);
}
