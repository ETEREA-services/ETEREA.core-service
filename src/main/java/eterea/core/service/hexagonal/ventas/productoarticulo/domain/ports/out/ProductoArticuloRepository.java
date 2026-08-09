package eterea.core.service.hexagonal.ventas.productoarticulo.domain.ports.out;

import eterea.core.service.hexagonal.ventas.productoarticulo.domain.model.ProductoArticulo;

import java.util.List;
import java.util.Optional;

public interface ProductoArticuloRepository {

    List<ProductoArticulo> findAllByProductoId(Integer productoId);

    Optional<ProductoArticulo> findByProductoIdAndArticuloId(Integer productoId, String articuloId);

    ProductoArticulo save(ProductoArticulo productoArticulo);

    void deleteById(Long productoarticuloId);

}
