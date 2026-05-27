package eterea.core.service.hexagonal.articulo.domain.ports.in;

import eterea.core.service.hexagonal.articulo.domain.model.Articulo;

public interface FindArticuloByAutoNumericoUseCase {
    Articulo findByAutoNumerico(Long autoNumerico);
}
