package eterea.core.service.hexagonal.articulo.domain.ports.in;

import eterea.core.service.hexagonal.articulo.domain.model.Articulo;

public interface UpdateArticuloUseCase {
    Articulo updateArticulo(String articuloId, Articulo articulo);
}
