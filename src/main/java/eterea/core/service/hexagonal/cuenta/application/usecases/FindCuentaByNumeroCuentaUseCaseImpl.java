package eterea.core.service.hexagonal.cuenta.application.usecases;

import eterea.core.service.hexagonal.cuenta.domain.model.Cuenta;
import eterea.core.service.hexagonal.cuenta.domain.ports.in.FindCuentaByNumeroCuentaUseCase;
import eterea.core.service.hexagonal.cuenta.domain.ports.out.CuentaRepository;
import eterea.core.service.kotlin.exception.CuentaException;
import org.springframework.stereotype.Component;

@Component
public class FindCuentaByNumeroCuentaUseCaseImpl implements FindCuentaByNumeroCuentaUseCase {

    private final CuentaRepository repository;

    public FindCuentaByNumeroCuentaUseCaseImpl(CuentaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Cuenta findByNumeroCuenta(Long numeroCuenta) {
        return repository.findByNumeroCuenta(numeroCuenta)
                .orElseThrow(() -> new CuentaException(numeroCuenta));
    }
}
