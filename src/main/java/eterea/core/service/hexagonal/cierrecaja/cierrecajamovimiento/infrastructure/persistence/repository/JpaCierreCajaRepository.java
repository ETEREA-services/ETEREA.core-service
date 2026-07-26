package eterea.core.service.hexagonal.cierrecaja.cierrecajamovimiento.infrastructure.persistence.repository;

import eterea.core.service.hexagonal.cierrecaja.cierrecajamovimiento.infrastructure.persistence.entity.CierreCajaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaCierreCajaRepository extends JpaRepository<CierreCajaEntity, Long> {

    Optional<CierreCajaEntity> findByCierreCajaId(Long cierreCajaId);

    Optional<CierreCajaEntity> findTopByOrderByFechaTopeDesc();

}
