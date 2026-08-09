package eterea.core.service.hexagonal.contable.cuenta.application.usecases;

import eterea.core.service.hexagonal.contable.cuenta.domain.model.Cuenta;
import eterea.core.service.hexagonal.contable.cuenta.domain.ports.in.FindCuentaByCuentaMaestroUseCase;
import eterea.core.service.hexagonal.contable.cuenta.domain.ports.out.CuentaRepository;
import eterea.core.service.kotlin.exception.CuentaException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class FindCuentaByCuentaMaestroUseCaseImpl implements FindCuentaByCuentaMaestroUseCase {

    private final CuentaRepository repository;

    @Override
    public Cuenta findByCuentaMaestro(BigDecimal cuentaMaestro) {
        return repository.findByCuentaMaestro(cuentaMaestro)
                .orElseThrow(() -> new CuentaException(cuentaMaestro));
    }
}
