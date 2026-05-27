package eterea.core.service.hexagonal.cuenta.domain.ports.out;

import eterea.core.service.hexagonal.cuenta.domain.model.Cuenta;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface CuentaRepository {

    List<Cuenta> findAll();

    List<Cuenta> findAllByCuentaMaestroGreaterThan(BigDecimal zero);

    Optional<Cuenta> findByNumeroCuenta(Long numeroCuenta);

    Optional<Cuenta> findByCuentaMaestro(BigDecimal cuentaMaestro);

}
