package eterea.core.service.hexagonal.articulomovimiento.infrastructure.persistence.repository;

import eterea.core.service.hexagonal.articulomovimiento.infrastructure.persistence.entity.ArticuloMovimientoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaArticuloMovimientoRepository extends JpaRepository<ArticuloMovimientoEntity, Long> {

    List<ArticuloMovimientoEntity> findAllByStockMovimientoId(Long stockMovimientoId);
    List<ArticuloMovimientoEntity> findAllByClienteMovimientoId(Long clienteMovimientoId);
    Optional<ArticuloMovimientoEntity> findByArticuloMovimientoId(Long articuloMovimientoId);

}
