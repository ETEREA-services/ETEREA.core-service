package eterea.core.service.hexagonal.ventas.productoarticulo.domain.ports.in;

import eterea.core.service.hexagonal.ventas.productoarticulo.domain.model.ProductoArticulo;

public interface CreateProductoArticuloUseCase {

    ProductoArticulo add(ProductoArticulo productoArticulo);

}
