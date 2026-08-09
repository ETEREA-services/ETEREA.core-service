package eterea.core.service.hexagonal.ventas.productoarticulo.domain.ports.in;

import eterea.core.service.hexagonal.ventas.productoarticulo.domain.model.ProductoArticulo;

import java.util.List;

public interface FindProductoArticulosByProductoIdUseCase {

    List<ProductoArticulo> findAllByProductoId(Integer productoId);

}
