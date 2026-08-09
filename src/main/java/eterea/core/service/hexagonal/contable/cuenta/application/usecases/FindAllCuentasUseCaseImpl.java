package eterea.core.service.hexagonal.contable.cuenta.application.usecases;

import eterea.core.service.hexagonal.contable.cuenta.domain.model.Cuenta;
import eterea.core.service.hexagonal.contable.cuenta.domain.ports.in.FindAllCuentasUseCase;
import eterea.core.service.hexagonal.contable.cuenta.domain.ports.out.CuentaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FindAllCuentasUseCaseImpl implements FindAllCuentasUseCase {

    private final CuentaRepository repository;

    @Override
    public List<Cuenta> findAll() {
        return repository.findAll();
    }
}
