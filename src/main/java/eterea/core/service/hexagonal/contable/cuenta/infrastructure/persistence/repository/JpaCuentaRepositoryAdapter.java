package eterea.core.service.hexagonal.contable.cuenta.infrastructure.persistence.repository;

import eterea.core.service.hexagonal.contable.cuenta.domain.model.Cuenta;
import eterea.core.service.hexagonal.contable.cuenta.domain.ports.out.CuentaRepository;
import eterea.core.service.hexagonal.contable.cuenta.infrastructure.persistence.mapper.CuentaMapper;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class JpaCuentaRepositoryAdapter implements CuentaRepository {

    private final JpaCuentaRepository jpaRepository;
    private final CuentaMapper mapper;

    public JpaCuentaRepositoryAdapter(JpaCuentaRepository jpaRepository, CuentaMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public List<Cuenta> findAll() {
        return jpaRepository.findAll().stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Cuenta> findAllByCuentaMaestroGreaterThan(BigDecimal zero) {
        return jpaRepository.findAllByCuentaMaestroGreaterThan(zero).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Cuenta> findByNumeroCuenta(Long numeroCuenta) {
        return jpaRepository.findByNumeroCuenta(numeroCuenta)
                .map(mapper::toDomain);
    }

    @Override
    public Optional<Cuenta> findByCuentaMaestro(BigDecimal cuentaMaestro) {
        return jpaRepository.findByCuentaMaestro(cuentaMaestro)
                .map(mapper::toDomain);
    }
}
