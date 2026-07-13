package eterea.core.service.hexagonal.stock.articulo.domain.ports.out;

import eterea.core.service.hexagonal.stock.articulo.domain.model.Articulo;

import java.util.List;
import java.util.Optional;

public interface ArticuloRepository {
    List<Articulo> findAll();
    List<Articulo> findByDescripcionLike(String chain);
    Optional<Articulo> findByArticuloId(String articuloId);
    Optional<Articulo> findByAutoNumericoId(Long autoNumericoId);
    Optional<Articulo> findByMascaraBalanza(String mascaraBalanza);
    Articulo save(Articulo articulo);
}
