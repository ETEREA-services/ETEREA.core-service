package eterea.core.service.hexagonal.cuenta.application.service;

import eterea.core.service.hexagonal.cuenta.domain.model.Cuenta;
import eterea.core.service.hexagonal.cuenta.domain.ports.in.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CuentaService {

    private final FindAllCuentasUseCase findAllCuentasUseCase;
    private final FindAllCuentasWithCuentaMaestroUseCase findAllCuentasWithCuentaMaestroUseCase;
    private final FindCuentaByNumeroCuentaUseCase findCuentaByNumeroCuentaUseCase;
    private final FindCuentaByCuentaMaestroUseCase findCuentaByCuentaMaestroUseCase;

    public CuentaService(FindAllCuentasUseCase findAllCuentasUseCase,
                         FindAllCuentasWithCuentaMaestroUseCase findAllCuentasWithCuentaMaestroUseCase,
                         FindCuentaByNumeroCuentaUseCase findCuentaByNumeroCuentaUseCase,
                         FindCuentaByCuentaMaestroUseCase findCuentaByCuentaMaestroUseCase) {
        this.findAllCuentasUseCase = findAllCuentasUseCase;
        this.findAllCuentasWithCuentaMaestroUseCase = findAllCuentasWithCuentaMaestroUseCase;
        this.findCuentaByNumeroCuentaUseCase = findCuentaByNumeroCuentaUseCase;
        this.findCuentaByCuentaMaestroUseCase = findCuentaByCuentaMaestroUseCase;
    }

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
