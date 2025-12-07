package eterea.core.service.hexagonal.proveedormovimiento.infrastructure.persistence.repository;

import eterea.core.service.hexagonal.proveedormovimiento.infrastructure.persistence.entity.ProveedorMovimientoEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

public interface ProveedorMovimientoJpaRepository extends JpaRepository<ProveedorMovimientoEntity, Integer> {

    List<ProveedorMovimientoEntity> findAllByProveedorIdOrderByFechaComprobante(Long proveedorId);
    List<ProveedorMovimientoEntity> findAllByFechaContableBetweenAndComprobanteLibroIva(
            OffsetDateTime desde,
            OffsetDateTime hasta,
            Byte libroIva,
            Sort sort
    );

    Optional<ProveedorMovimientoEntity> findByProveedorMovimientoId(Long proveedorMovimientoId);
}
