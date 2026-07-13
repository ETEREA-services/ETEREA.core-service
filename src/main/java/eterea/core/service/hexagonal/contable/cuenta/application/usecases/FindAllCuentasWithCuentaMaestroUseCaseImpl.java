package eterea.core.service.hexagonal.contable.cuenta.application.usecases;

import eterea.core.service.hexagonal.contable.cuenta.domain.model.Cuenta;
import eterea.core.service.hexagonal.contable.cuenta.domain.ports.in.FindAllCuentasWithCuentaMaestroUseCase;
import eterea.core.service.hexagonal.contable.cuenta.domain.ports.out.CuentaRepository;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class FindAllCuentasWithCuentaMaestroUseCaseImpl implements FindAllCuentasWithCuentaMaestroUseCase {

    private final CuentaRepository repository;

    public FindAllCuentasWithCuentaMaestroUseCaseImpl(CuentaRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Cuenta> findAllWithCuentaMaestro() {
        return repository.findAllByCuentaMaestroGreaterThan(BigDecimal.ZERO);
    }
}
