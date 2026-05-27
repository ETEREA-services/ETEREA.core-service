package eterea.core.service.hexagonal.articulo.domain.ports.in;

import eterea.core.service.hexagonal.articulo.domain.model.Articulo;
import java.util.List;

public interface FindArticulosBySearchUseCase {
    List<Articulo> findBySearch(String chain);
}
