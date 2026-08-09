package eterea.core.service.hexagonal.ventas.productoarticulo.application.usecases;

import eterea.core.service.hexagonal.ventas.productoarticulo.application.exception.ProductoArticuloException;
import eterea.core.service.hexagonal.ventas.productoarticulo.domain.model.ProductoArticulo;
import eterea.core.service.hexagonal.ventas.productoarticulo.domain.ports.in.FindProductoArticuloByProductoIdAndArticuloIdUseCase;
import eterea.core.service.hexagonal.ventas.productoarticulo.domain.ports.out.ProductoArticuloRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindProductoArticuloByProductoIdAndArticuloIdUseCaseImpl implements FindProductoArticuloByProductoIdAndArticuloIdUseCase {

    private final ProductoArticuloRepository productoArticuloRepository;

    @Override
    public ProductoArticulo findByProductoIdAndArticuloId(Integer productoId, String articuloId) {
        return productoArticuloRepository.findByProductoIdAndArticuloId(productoId, articuloId)
                .orElseThrow(() -> new ProductoArticuloException(productoId, articuloId));
    }

}
