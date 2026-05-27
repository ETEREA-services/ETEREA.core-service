package eterea.core.service.hexagonal.cuenta.infrastructure.persistence.repository;

import eterea.core.service.hexagonal.cuenta.infrastructure.persistence.entity.CuentaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface JpaCuentaRepository extends JpaRepository<CuentaEntity, Long> {

    List<CuentaEntity> findAllByCuentaMaestroGreaterThan(BigDecimal zero);

    Optional<CuentaEntity> findByNumeroCuenta(Long numeroCuenta);

    Optional<CuentaEntity> findByCuentaMaestro(BigDecimal cuentaMaestro);

}
