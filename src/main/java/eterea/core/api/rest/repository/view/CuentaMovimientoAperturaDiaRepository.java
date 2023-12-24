package eterea.core.api.rest.repository.view;

import eterea.core.api.rest.kotlin.model.view.CuentaMovimientoAperturaDia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;

@Repository
public interface CuentaMovimientoAperturaDiaRepository extends JpaRepository<CuentaMovimientoAperturaDia, String> {

    public List<CuentaMovimientoAperturaDia> findAllByNumeroCuentaAndDebitaAndFechaBetween(Long numeroCuenta, int i, OffsetDateTime desde, OffsetDateTime hasta);

}
