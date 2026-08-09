package eterea.core.service.hexagonal.ventas.productoarticulo.domain.ports.in;

import eterea.core.service.hexagonal.ventas.productoarticulo.domain.model.ProductoArticulo;

public interface FindProductoArticuloByProductoIdAndArticuloIdUseCase {

    ProductoArticulo findByProductoIdAndArticuloId(Integer productoId, String articuloId);

}
