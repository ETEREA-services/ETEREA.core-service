package eterea.core.service.hexagonal.ventas.productoarticulo.application.usecases;

import eterea.core.service.hexagonal.ventas.productoarticulo.domain.model.ProductoArticulo;
import eterea.core.service.hexagonal.ventas.productoarticulo.domain.ports.in.FindProductoArticulosByProductoIdUseCase;
import eterea.core.service.hexagonal.ventas.productoarticulo.domain.ports.out.ProductoArticuloRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FindProductoArticulosByProductoIdUseCaseImpl implements FindProductoArticulosByProductoIdUseCase {

    private final ProductoArticuloRepository productoArticuloRepository;

    @Override
    public List<ProductoArticulo> findAllByProductoId(Integer productoId) {
        return productoArticuloRepository.findAllByProductoId(productoId);
    }

}
