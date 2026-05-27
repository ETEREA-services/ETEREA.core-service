package eterea.core.service.hexagonal.comprobante.domain.ports.in;

import eterea.core.service.hexagonal.comprobante.domain.model.Comprobante;

public interface FindComprobanteByIdUseCase {
    Comprobante findByComprobanteId(Integer comprobanteId);
}
