package eterea.core.service.hexagonal.articulo.infrastructure.persistence.repository;

import eterea.core.service.hexagonal.articulo.domain.model.Articulo;
import eterea.core.service.hexagonal.articulo.domain.ports.out.ArticuloRepository;
import eterea.core.service.hexagonal.articulo.infrastructure.persistence.entity.ArticuloEntity;
import eterea.core.service.hexagonal.articulo.infrastructure.persistence.mapper.ArticuloMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ArticuloRepositoryAdapter implements ArticuloRepository {

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
