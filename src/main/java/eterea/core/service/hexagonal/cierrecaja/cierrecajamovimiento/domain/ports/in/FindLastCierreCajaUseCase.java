package eterea.core.service.hexagonal.cierrecaja.cierrecajamovimiento.domain.ports.in;

import eterea.core.service.hexagonal.cierrecaja.cierrecajamovimiento.domain.model.CierreCaja;

public interface FindLastCierreCajaUseCase {
    CierreCaja findLast();
}
