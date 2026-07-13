package eterea.core.service.hexagonal.stock.articulomovimiento.infrastructure.persistence.repository;

import eterea.core.service.hexagonal.stock.articulomovimiento.domain.model.ArticuloMovimiento;
import eterea.core.service.hexagonal.stock.articulomovimiento.domain.ports.out.ArticuloMovimientoRepository;
import eterea.core.service.hexagonal.stock.articulomovimiento.infrastructure.persistence.mapper.ArticuloMovimientoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JpaArticuloMovimientoRepositoryAdapter implements ArticuloMovimientoRepository {

    private final JpaArticuloMovimientoRepository jpaRepository;
    private final ArticuloMovimientoMapper mapper;

    @Override
    public List<ArticuloMovimiento> findAllByClienteMovimientoId(Long clienteMovimientoId) {
        var entities = jpaRepository.findAllByClienteMovimientoId(clienteMovimientoId);
        if (entities == null) return Collections.emptyList();
        return entities.stream().map(mapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public List<ArticuloMovimiento> findAllByStockMovimientoId(Long stockMovimientoId) {
        var entities = jpaRepository.findAllByStockMovimientoId(stockMovimientoId);
        if (entities == null) return Collections.emptyList();
        return entities.stream().map(mapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public Optional<ArticuloMovimiento> findByArticuloMovimientoId(Long articuloMovimientoId) {
        var entity = jpaRepository.findByArticuloMovimientoId(articuloMovimientoId);
        if(entity.isEmpty()) return Optional.empty();
        return entity.map(mapper::toDomain);
    }

    @Override
    public ArticuloMovimiento save(ArticuloMovimiento articuloMovimiento) {
        var entity = mapper.toEntity(articuloMovimiento);
        return mapper.toDomain(jpaRepository.save(entity));
    }

    @Override
    public List<ArticuloMovimiento> saveAll(List<ArticuloMovimiento> articuloMovimientos) {
        var entities = articuloMovimientos.stream().map(mapper::toEntity).collect(Collectors.toList());
        return jpaRepository.saveAll(entities).stream().map(mapper::toDomain).collect(Collectors.toList());
    }
}
