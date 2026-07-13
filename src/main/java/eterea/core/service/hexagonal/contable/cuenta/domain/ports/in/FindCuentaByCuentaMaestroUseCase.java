package eterea.core.service.hexagonal.contable.cuenta.domain.ports.in;

import eterea.core.service.hexagonal.contable.cuenta.domain.model.Cuenta;
import java.math.BigDecimal;

public interface FindCuentaByCuentaMaestroUseCase {
    Cuenta findByCuentaMaestro(BigDecimal cuentaMaestro);
}
