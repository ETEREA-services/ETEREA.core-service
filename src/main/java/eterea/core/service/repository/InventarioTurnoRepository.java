package eterea.core.service.repository;

import eterea.core.service.kotlin.model.InventarioTurno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InventarioTurnoRepository extends JpaRepository<InventarioTurno, Integer> {

    Optional<InventarioTurno> findByInventarioTurnoId(Integer inventarioTurnoId);

    Optional<InventarioTurno> findFirstByOrderByInventarioTurnoIdDesc();

}
