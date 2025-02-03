package eterea.core.service.repository;

import eterea.core.service.kotlin.model.MonedaCotizacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface MonedaCotizacionRepository extends JpaRepository<MonedaCotizacion, UUID> {

    List<MonedaCotizacion> findAllByMonedaIdAndFechaBetween(Integer monedaId, OffsetDateTime fechaDesde, OffsetDateTime fechaHasta);

    boolean existsByMonedaIdAndFecha(Integer monedaId, OffsetDateTime fecha);

}
