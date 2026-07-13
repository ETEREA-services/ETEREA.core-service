package eterea.core.service.hexagonal.contable.cuenta.domain.ports.in;

import eterea.core.service.hexagonal.contable.cuenta.domain.model.Cuenta;
import java.util.List;

public interface FindAllCuentasUseCase {
    List<Cuenta> findAll();
}
