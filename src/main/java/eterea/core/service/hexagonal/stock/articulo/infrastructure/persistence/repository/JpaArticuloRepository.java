package eterea.core.service.hexagonal.stock.articulo.infrastructure.persistence.repository;

import eterea.core.service.hexagonal.stock.articulo.infrastructure.persistence.entity.ArticuloEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaArticuloRepository extends JpaRepository<ArticuloEntity, String> {

    List<ArticuloEntity> findTop50ByDescripcionLikeOrderByDescripcion(String descripcion);

    Optional<ArticuloEntity> findByAutoNumericoId(Long autoNumericoId);

    Optional<ArticuloEntity> findByArticuloId(String articuloId);

    Optional<ArticuloEntity> findByMascaraBalanza(String mascaraBalanza);

}
