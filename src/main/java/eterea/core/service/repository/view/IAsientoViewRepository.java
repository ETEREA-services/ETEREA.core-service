package eterea.core.service.repository.view;

import eterea.core.service.kotlin.model.view.AsientoView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;

@Repository
public interface IAsientoViewRepository extends JpaRepository<AsientoView, String> {

    List<AsientoView> findAllByFechaBetween(OffsetDateTime desde, OffsetDateTime hasta);

    List<AsientoView> findAllByNegocioIdAndFecha(Integer negocioId, OffsetDateTime fecha);

}
