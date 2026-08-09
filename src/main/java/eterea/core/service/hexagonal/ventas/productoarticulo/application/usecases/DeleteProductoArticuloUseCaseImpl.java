package eterea.core.service.hexagonal.ventas.productoarticulo.application.usecases;

import eterea.core.service.hexagonal.ventas.productoarticulo.domain.ports.in.DeleteProductoArticuloUseCase;
import eterea.core.service.hexagonal.ventas.productoarticulo.domain.ports.out.ProductoArticuloRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeleteProductoArticuloUseCaseImpl implements DeleteProductoArticuloUseCase {

    private final ProductoArticuloRepository productoArticuloRepository;

    @Override
    public void delete(Long productoarticuloId) {
        productoArticuloRepository.deleteById(productoarticuloId);
    }

}
