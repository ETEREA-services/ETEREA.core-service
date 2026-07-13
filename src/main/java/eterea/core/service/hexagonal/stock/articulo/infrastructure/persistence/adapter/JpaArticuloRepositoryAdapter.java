package eterea.core.service.hexagonal.stock.articulo.infrastructure.persistence.adapter;

import eterea.core.service.hexagonal.stock.articulo.domain.model.Articulo;
import eterea.core.service.hexagonal.stock.articulo.domain.ports.out.ArticuloRepository;
import eterea.core.service.hexagonal.stock.articulo.infrastructure.persistence.entity.ArticuloEntity;
import eterea.core.service.hexagonal.stock.articulo.infrastructure.persistence.mapper.ArticuloMapper;
import eterea.core.service.hexagonal.stock.articulo.infrastructure.persistence.repository.JpaArticuloRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JpaArticuloRepositoryAdapter implements ArticuloRepository {

    private final JpaArticuloRepository jpaArticuloRepository;
    private final ArticuloMapper articuloMapper;

    @Override
    public List<Articulo> findAll() {
        return jpaArticuloRepository.findAll().stream()
                .map(articuloMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Articulo> findByDescripcionLike(String chain) {
        return jpaArticuloRepository.findTop50ByDescripcionLikeOrderByDescripcion(chain).stream()
                .map(articuloMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Articulo> findByArticuloId(String articuloId) {
        return jpaArticuloRepository.findByArticuloId(articuloId)
                .map(articuloMapper::toDomain);
    }

    @Override
    public Optional<Articulo> findByAutoNumericoId(Long autoNumericoId) {
        return jpaArticuloRepository.findByAutoNumericoId(autoNumericoId)
                .map(articuloMapper::toDomain);
    }

    @Override
    public Optional<Articulo> findByMascaraBalanza(String mascaraBalanza) {
        return jpaArticuloRepository.findByMascaraBalanza(mascaraBalanza)
                .map(articuloMapper::toDomain);
    }

    @Override
    public Articulo save(Articulo articulo) {
        ArticuloEntity entity = articuloMapper.toEntity(articulo);
        ArticuloEntity savedEntity = jpaArticuloRepository.save(entity);
        return articuloMapper.toDomain(savedEntity);
    }
}
