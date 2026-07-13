package eterea.core.service.hexagonal.tesoreria.valormovimiento.infrastructure.persistence.adapter;

import eterea.core.service.hexagonal.tesoreria.valormovimiento.domain.model.ValorMovimiento;
import eterea.core.service.hexagonal.tesoreria.valormovimiento.domain.ports.out.ValorMovimientoRepository;
import eterea.core.service.hexagonal.tesoreria.valormovimiento.infrastructure.persistence.mapper.ValorMovimientoMapper;
import eterea.core.service.hexagonal.tesoreria.valormovimiento.infrastructure.persistence.repository.JpaValorMovimientoRepository;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class JpaValorMovimientoRepositoryAdapter implements ValorMovimientoRepository {

    private final JpaValorMovimientoRepository jpaRepository;
    private final ValorMovimientoMapper mapper;

    public JpaValorMovimientoRepositoryAdapter(JpaValorMovimientoRepository jpaRepository,
                                               ValorMovimientoMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Optional<ValorMovimiento> findByValorMovimientoId(Long valorMovimientoId) {
        return jpaRepository.findByValorMovimientoId(valorMovimientoId)
                .map(mapper::toDomain);
    }

    @Override
    public List<ValorMovimiento> findAllByFechaContableBetween(OffsetDateTime desde, OffsetDateTime hasta,
                                                               Boolean cierreCajaOnly, Boolean ingresosOnly) {
        return jpaRepository.findAllByFechaContableBetween(desde, hasta, cierreCajaOnly, ingresosOnly).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<ValorMovimiento> findAllByFechaContableAndOrdenContable(OffsetDateTime fechaContable,
                                                                        Integer ordenContable) {
        return jpaRepository.findAllByFechaContableAndOrdenContable(fechaContable, ordenContable).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAllByFechaContableAndOrdenContable(OffsetDateTime fechaContable, Integer ordenContable) {
        jpaRepository.deleteAllByFechaContableAndOrdenContable(fechaContable, ordenContable);
    }

    @Override
    public ValorMovimiento save(ValorMovimiento valorMovimiento) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(valorMovimiento)));
    }

    @Override
    public List<ValorMovimiento> saveAll(List<ValorMovimiento> valorMovimientos) {
        return jpaRepository.saveAll(valorMovimientos.stream()
                        .map(mapper::toEntity)
                        .collect(Collectors.toList()))
                .stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

}
