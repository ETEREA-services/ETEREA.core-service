package eterea.core.api.rest.repository.view;

import eterea.core.api.rest.kotlin.model.view.CuentaMovimientoDia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;

@Repository
public interface CuentaMovimientoDiaRepository extends JpaRepository<CuentaMovimientoDia, String> {
    public List<CuentaMovimientoDia> findAllByNumeroCuentaAndDebitaAndInflacionAndFechaBetween(Long numeroCuenta, Integer debita, Integer inflacion, OffsetDateTime desde, OffsetDateTime hasta);

}
