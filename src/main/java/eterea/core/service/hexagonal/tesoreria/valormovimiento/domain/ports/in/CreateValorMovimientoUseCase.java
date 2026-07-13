package eterea.core.service.hexagonal.tesoreria.valormovimiento.domain.ports.in;

import eterea.core.service.hexagonal.tesoreria.valormovimiento.domain.model.ValorMovimiento;

public interface CreateValorMovimientoUseCase {
    ValorMovimiento create(ValorMovimiento valorMovimiento);
}
