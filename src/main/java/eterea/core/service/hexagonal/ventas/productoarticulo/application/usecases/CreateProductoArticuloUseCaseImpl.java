package eterea.core.service.hexagonal.ventas.productoarticulo.application.usecases;

import eterea.core.service.hexagonal.ventas.productoarticulo.domain.model.ProductoArticulo;
import eterea.core.service.hexagonal.ventas.productoarticulo.domain.ports.in.CreateProductoArticuloUseCase;
import eterea.core.service.hexagonal.ventas.productoarticulo.domain.ports.out.ProductoArticuloRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateProductoArticuloUseCaseImpl implements CreateProductoArticuloUseCase {

    private final ProductoArticuloRepository productoArticuloRepository;

    @Override
    public ProductoArticulo add(ProductoArticulo productoArticulo) {
        return productoArticuloRepository.save(productoArticulo);
    }

}
