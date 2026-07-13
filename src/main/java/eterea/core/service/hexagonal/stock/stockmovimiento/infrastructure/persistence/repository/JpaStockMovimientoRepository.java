package eterea.core.service.hexagonal.stock.stockmovimiento.infrastructure.persistence.repository;

import eterea.core.service.hexagonal.stock.stockmovimiento.infrastructure.persistence.entity.StockMovimientoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface JpaStockMovimientoRepository extends JpaRepository<StockMovimientoEntity, Long> {

    Optional<StockMovimientoEntity> findFirstByComprobanteIdOrderByStockMovimientoIdDesc(Integer comprobanteId);

    Optional<StockMovimientoEntity> findByStockMovimientoId(Long stockMovimientoId);

    List<StockMovimientoEntity> findAllByCierreCajaIdAndFechaComprobanteBetween(Long cierreCajaId, OffsetDateTime desde, OffsetDateTime hasta);

}
