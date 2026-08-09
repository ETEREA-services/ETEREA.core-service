package eterea.core.service.hexagonal.ventas.productoarticulo.infrastructure.persistence.adapter;

import eterea.core.service.hexagonal.ventas.productoarticulo.domain.model.ProductoArticulo;
import eterea.core.service.hexagonal.ventas.productoarticulo.domain.ports.out.ProductoArticuloRepository;
import eterea.core.service.hexagonal.ventas.productoarticulo.infrastructure.persistence.mapper.ProductoArticuloMapper;
import eterea.core.service.hexagonal.ventas.productoarticulo.infrastructure.persistence.repository.JpaProductoArticuloRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JpaProductoArticuloRepositoryAdapter implements ProductoArticuloRepository {

    private final JpaProductoArticuloRepository jpaRepository;
    private final ProductoArticuloMapper mapper;

    @Override
    public List<ProductoArticulo> findAllByProductoId(Integer productoId) {
        return jpaRepository.findAllByProductoId(productoId)
                .stream().map(mapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public Optional<ProductoArticulo> findByProductoIdAndArticuloId(Integer productoId, String articuloId) {
        return jpaRepository.findByProductoIdAndArticuloId(productoId, articuloId)
                .map(mapper::toDomain);
    }

    @Override
    public ProductoArticulo save(ProductoArticulo productoArticulo) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(productoArticulo)));
    }

    @Override
    public void deleteById(Long productoarticuloId) {
        jpaRepository.deleteById(productoarticuloId);
    }

}
