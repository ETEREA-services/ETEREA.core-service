package eterea.core.service.service;

import eterea.core.service.kotlin.exception.CuentaException;
import eterea.core.service.kotlin.model.Cuenta;
import eterea.core.service.kotlin.repository.CuentaRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Service
public class CuentaService {

    private final CuentaRepository repository;

    public CuentaService(CuentaRepository repository) {
        this.repository = repository;
    }

    public List<Cuenta> findAll() {
        return repository.findAll();
    }

    public List<Cuenta> findAllWithCuentaMaestro() {
        return repository.findAllByCuentaMaestroGreaterThan(BigDecimal.ZERO);
    }

    public Cuenta findByNumeroCuenta(Long numeroCuenta) {
        return Objects.requireNonNull(repository.findByNumeroCuenta(numeroCuenta)).orElseThrow(() -> new CuentaException(numeroCuenta));
    }

    public Cuenta findByCuentaMaestro(BigDecimal cuentaMaestro) {
        return Objects.requireNonNull(repository.findByCuentaMaestro(cuentaMaestro)).orElseThrow(() -> new CuentaException(cuentaMaestro));
    }

}
