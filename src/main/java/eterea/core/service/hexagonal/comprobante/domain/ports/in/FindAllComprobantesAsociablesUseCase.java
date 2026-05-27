package eterea.core.service.hexagonal.comprobante.domain.ports.in;

import eterea.core.service.hexagonal.comprobante.domain.model.Comprobante;
import java.util.List;

public interface FindAllComprobantesAsociablesUseCase {
    List<Comprobante> findAllAsociables();
    List<Comprobante> findAllAsociables(Byte debita);
}
