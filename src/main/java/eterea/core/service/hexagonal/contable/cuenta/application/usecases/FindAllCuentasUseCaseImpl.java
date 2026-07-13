package eterea.core.service.hexagonal.contable.cuenta.application.usecases;

import eterea.core.service.hexagonal.contable.cuenta.domain.model.Cuenta;
import eterea.core.service.hexagonal.contable.cuenta.domain.ports.in.FindAllCuentasUseCase;
import eterea.core.service.hexagonal.contable.cuenta.domain.ports.out.CuentaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FindAllCuentasUseCaseImpl implements FindAllCuentasUseCase {

    private final CuentaRepository repository;

    public FindAllCuentasUseCaseImpl(CuentaRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Cuenta> findAll() {
        return repository.findAll();
    }
}
