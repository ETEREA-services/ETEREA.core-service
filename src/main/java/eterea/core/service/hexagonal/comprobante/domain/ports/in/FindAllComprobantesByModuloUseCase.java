package eterea.core.service.hexagonal.comprobante.domain.ports.in;

import eterea.core.service.hexagonal.comprobante.domain.model.Comprobante;
import java.util.List;

public interface FindAllComprobantesByModuloUseCase {
    List<Comprobante> findAllByModulo(Integer modulo, Byte debita, Integer comprobanteId);
}
