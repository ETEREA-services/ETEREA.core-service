package eterea.core.service.service;

import eterea.core.service.kotlin.exception.CuentaException;
import eterea.core.service.kotlin.model.Cuenta;
import eterea.core.service.kotlin.repository.CuentaRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CuentaService {

    private final CuentaRepository repository;

    public CuentaService(CuentaRepository repository) {
        this.repository = repository;
    }

    public Cuenta findByNumeroCuenta(Long numeroCuenta) {
        return Objects.requireNonNull(repository.findByNumeroCuenta(numeroCuenta)).orElseThrow(() -> new CuentaException(numeroCuenta));
    }

}
