package eterea.core.api.rest.repository.view;

import eterea.core.api.rest.model.view.AsientoView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;

@Repository
public interface AsientoViewRepository extends JpaRepository<AsientoView, String> {

    public List<AsientoView> findAllByFechaBetween(OffsetDateTime desde, OffsetDateTime hasta);

}
