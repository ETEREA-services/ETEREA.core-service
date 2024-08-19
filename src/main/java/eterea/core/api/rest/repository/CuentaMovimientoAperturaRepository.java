package eterea.core.api.rest.repository;

import eterea.core.api.rest.kotlin.model.CuentaMovimientoApertura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuentaMovimientoAperturaRepository extends JpaRepository<CuentaMovimientoApertura, Long>, CuentaMovimientoAperturaRepositoryCustom {
}
