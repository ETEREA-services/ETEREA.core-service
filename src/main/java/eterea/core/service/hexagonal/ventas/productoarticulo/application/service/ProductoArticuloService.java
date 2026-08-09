package eterea.core.service.hexagonal.ventas.productoarticulo.application.service;

import eterea.core.service.hexagonal.ventas.productoarticulo.domain.model.ProductoArticulo;
import eterea.core.service.hexagonal.ventas.productoarticulo.domain.ports.in.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductoArticuloService {

    private final FindProductoArticulosByProductoIdUseCase findProductoArticulosByProductoIdUseCase;
    private final FindProductoArticuloByProductoIdAndArticuloIdUseCase findProductoArticuloByProductoIdAndArticuloIdUseCase;
    private final CreateProductoArticuloUseCase createProductoArticuloUseCase;
    private final DeleteProductoArticuloUseCase deleteProductoArticuloUseCase;

    public List<ProductoArticulo> findAllByProductoId(Integer productoId) {
        return findProductoArticulosByProductoIdUseCase.findAllByProductoId(productoId);
    }

    public ProductoArticulo findByProductoIdAndArticuloId(Integer productoId, String articuloId) {
        return findProductoArticuloByProductoIdAndArticuloIdUseCase.findByProductoIdAndArticuloId(productoId, articuloId);
    }

    public ProductoArticulo add(ProductoArticulo productoArticulo) {
        return createProductoArticuloUseCase.add(productoArticulo);
    }

    public void delete(Long productoarticuloId) {
        deleteProductoArticuloUseCase.delete(productoarticuloId);
    }

}
