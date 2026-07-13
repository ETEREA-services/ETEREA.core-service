package eterea.core.service.hexagonal.stock.articulo.domain.ports.in;

import eterea.core.service.hexagonal.stock.articulo.domain.model.Articulo;

public interface FindArticuloByArticuloIdUseCase {
    Articulo findByArticuloId(String articuloId);
}
