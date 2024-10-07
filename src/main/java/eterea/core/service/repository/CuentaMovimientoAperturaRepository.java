package eterea.core.service.repository;

import eterea.core.service.kotlin.model.CuentaMovimientoApertura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuentaMovimientoAperturaRepository extends JpaRepository<CuentaMovimientoApertura, Long>, CuentaMovimientoAperturaRepositoryCustom {
}
