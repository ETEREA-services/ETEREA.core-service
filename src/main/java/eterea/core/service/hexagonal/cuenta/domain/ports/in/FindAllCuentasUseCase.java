package eterea.core.service.hexagonal.cuenta.domain.ports.in;

import eterea.core.service.hexagonal.cuenta.domain.model.Cuenta;
import java.util.List;

public interface FindAllCuentasUseCase {
    List<Cuenta> findAll();
}
