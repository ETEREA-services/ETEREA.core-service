package eterea.core.service.hexagonal.contable.cuenta.application.service;

import eterea.core.service.hexagonal.contable.cuenta.domain.model.Cuenta;
import eterea.core.service.hexagonal.contable.cuenta.domain.ports.in.FindAllCuentasUseCase;
import eterea.core.service.hexagonal.contable.cuenta.domain.ports.in.FindAllCuentasWithCuentaMaestroUseCase;
import eterea.core.service.hexagonal.contable.cuenta.domain.ports.in.FindCuentaByCuentaMaestroUseCase;
import eterea.core.service.hexagonal.contable.cuenta.domain.ports.in.FindCuentaByNumeroCuentaUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CuentaService {

    private final FindAllCuentasUseCase findAllCuentasUseCase;
    private final FindAllCuentasWithCuentaMaestroUseCase findAllCuentasWithCuentaMaestroUseCase;
    private final FindCuentaByNumeroCuentaUseCase findCuentaByNumeroCuentaUseCase;
    private final FindCuentaByCuentaMaestroUseCase findCuentaByCuentaMaestroUseCase;

    public List<Cuenta> findAll() {
        return findAllCuentasUseCase.findAll();
    }

    public List<Cuenta> findAllWithCuentaMaestro() {
        return findAllCuentasWithCuentaMaestroUseCase.findAllWithCuentaMaestro();
    }

    public Cuenta findByNumeroCuenta(Long numeroCuenta) {
        return findCuentaByNumeroCuentaUseCase.findByNumeroCuenta(numeroCuenta);
    }

    public Cuenta findByCuentaMaestro(BigDecimal cuentaMaestro) {
        return findCuentaByCuentaMaestroUseCase.findByCuentaMaestro(cuentaMaestro);
    }

}
