package eterea.core.api.rest.repository;

import eterea.core.api.rest.kotlin.model.InventarioTurno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InventarioTurnoRepository extends JpaRepository<InventarioTurno, Integer> {

    Optional<InventarioTurno> findByInventarioTurnoId(Integer inventarioTurnoId);

    Optional<InventarioTurno> findFirstByOrderByInventarioTurnoIdDesc();

}
