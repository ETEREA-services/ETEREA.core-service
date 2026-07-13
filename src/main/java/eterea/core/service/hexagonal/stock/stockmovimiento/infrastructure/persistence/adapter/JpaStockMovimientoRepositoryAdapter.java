package eterea.core.service.hexagonal.stock.stockmovimiento.infrastructure.persistence.adapter;

import eterea.core.service.hexagonal.stock.stockmovimiento.domain.model.StockMovimiento;
import eterea.core.service.hexagonal.stock.stockmovimiento.domain.ports.out.StockMovimientoRepository;
import eterea.core.service.hexagonal.stock.stockmovimiento.infrastructure.persistence.mapper.StockMovimientoMapper;
import eterea.core.service.hexagonal.stock.stockmovimiento.infrastructure.persistence.repository.JpaStockMovimientoRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class JpaStockMovimientoRepositoryAdapter implements StockMovimientoRepository {

    private final JpaStockMovimientoRepository jpaRepository;
    private final StockMovimientoMapper mapper;

    public JpaStockMovimientoRepositoryAdapter(JpaStockMovimientoRepository jpaRepository, StockMovimientoMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Optional<StockMovimiento> findByStockMovimientoId(Long stockMovimientoId) {
        return jpaRepository.findByStockMovimientoId(stockMovimientoId)
                .map(mapper::toDomain);
    }

    @Override
    public Optional<StockMovimiento> findFirstByComprobanteIdOrderByStockMovimientoIdDesc(Integer comprobanteId) {
        return jpaRepository.findFirstByComprobanteIdOrderByStockMovimientoIdDesc(comprobanteId)
                .map(mapper::toDomain);
    }

    @Override
    public StockMovimiento save(StockMovimiento stockMovimiento) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(stockMovimiento)));
    }

}
