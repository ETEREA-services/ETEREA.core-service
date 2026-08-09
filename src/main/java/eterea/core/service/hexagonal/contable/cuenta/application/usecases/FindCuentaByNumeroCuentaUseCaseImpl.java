package eterea.core.service.hexagonal.contable.cuenta.application.usecases;

import eterea.core.service.hexagonal.contable.cuenta.domain.model.Cuenta;
import eterea.core.service.hexagonal.contable.cuenta.domain.ports.in.FindCuentaByNumeroCuentaUseCase;
import eterea.core.service.hexagonal.contable.cuenta.domain.ports.out.CuentaRepository;
import eterea.core.service.kotlin.exception.CuentaException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindCuentaByNumeroCuentaUseCaseImpl implements FindCuentaByNumeroCuentaUseCase {

    private final CuentaRepository repository;

    @Override
    public Cuenta findByNumeroCuenta(Long numeroCuenta) {
        return repository.findByNumeroCuenta(numeroCuenta)
                .orElseThrow(() -> new CuentaException(numeroCuenta));
    }
}
