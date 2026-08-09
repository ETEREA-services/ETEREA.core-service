package eterea.core.service.hexagonal.ventas.productoarticulo.infrastructure.persistence.repository;

import eterea.core.service.hexagonal.ventas.productoarticulo.infrastructure.persistence.entity.ProductoArticuloEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaProductoArticuloRepository extends JpaRepository<ProductoArticuloEntity, Long> {

    List<ProductoArticuloEntity> findAllByProductoId(Integer productoId);

    Optional<ProductoArticuloEntity> findByProductoIdAndArticuloId(Integer productoId, String articuloId);

}
