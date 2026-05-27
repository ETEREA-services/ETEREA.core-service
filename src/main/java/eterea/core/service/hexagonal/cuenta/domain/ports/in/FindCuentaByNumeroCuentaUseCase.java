package eterea.core.service.hexagonal.cuenta.domain.ports.in;

import eterea.core.service.hexagonal.cuenta.domain.model.Cuenta;

public interface FindCuentaByNumeroCuentaUseCase {
    Cuenta findByNumeroCuenta(Long numeroCuenta);
}
