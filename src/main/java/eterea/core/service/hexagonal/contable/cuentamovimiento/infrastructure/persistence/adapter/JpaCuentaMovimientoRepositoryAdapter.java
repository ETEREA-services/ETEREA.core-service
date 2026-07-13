package eterea.core.service.hexagonal.contable.cuentamovimiento.infrastructure.persistence.adapter;

import eterea.core.service.hexagonal.contable.cuentamovimiento.domain.model.CuentaMovimiento;
import eterea.core.service.hexagonal.contable.cuentamovimiento.domain.ports.out.CuentaMovimientoRepository;
import eterea.core.service.hexagonal.contable.cuentamovimiento.infrastructure.persistence.mapper.CuentaMovimientoMapper;
import eterea.core.service.hexagonal.contable.cuentamovimiento.infrastructure.persistence.repository.JpaCuentaMovimientoRepository;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class JpaCuentaMovimientoRepositoryAdapter implements CuentaMovimientoRepository {

    private final JpaCuentaMovimientoRepository jpaRepository;
    private final CuentaMovimientoMapper mapper;

    public JpaCuentaMovimientoRepositoryAdapter(JpaCuentaMovimientoRepository jpaRepository, CuentaMovimientoMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public List<CuentaMovimiento> findAllByFechaAndOrden(OffsetDateTime fechaContable, Integer ordenContable) {
        return jpaRepository.findAllByFechaAndOrden(fechaContable, ordenContable).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<CuentaMovimiento> findAllByFechaBetween(OffsetDateTime fechaDesde, OffsetDateTime fechaHasta) {
        return jpaRepository.findAllByFechaBetween(fechaDesde, fechaHasta).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CuentaMovimiento> findByCuentaMovimientoId(Long cuentaMovimientoId) {
        return jpaRepository.findByCuentaMovimientoId(cuentaMovimientoId)
                .map(mapper::toDomain);
    }

    @Override
    public Optional<CuentaMovimiento> findFirstByFechaAndOrdenOrderByItemDesc(OffsetDateTime fecha, Integer orden) {
        return jpaRepository.findFirstByFechaAndOrdenOrderByItemDesc(fecha, orden)
                .map(mapper::toDomain);
    }

    @Override
    public Optional<CuentaMovimiento> findFirstByFechaOrderByOrdenDesc(OffsetDateTime fecha) {
        return jpaRepository.findFirstByFechaOrderByOrdenDesc(fecha)
                .map(mapper::toDomain);
    }

    @Override
    public BigDecimal calculateTotalByNumeroCuentaAndDebitaAndFechaBetween(Long numeroCuenta, Integer debita, OffsetDateTime desde, OffsetDateTime hasta) {
        return jpaRepository.calculateTotalByNumeroCuentaAndDebitaAndFechaBetween(numeroCuenta, debita, desde, hasta);
    }

    @Override
    public BigDecimal calculateTotalByNumeroCuentaAndDebitaAndInflacionAndFechaBetween(Long numeroCuenta, Integer debita, Integer inflacion, OffsetDateTime desde, OffsetDateTime hasta) {
        return jpaRepository.calculateTotalByNumeroCuentaAndDebitaAndInflacionAndFechaBetween(numeroCuenta, debita, inflacion, desde, hasta);
    }

    @Override
    public List<CuentaMovimiento> saveAll(List<CuentaMovimiento> cuentaMovimientos) {
        return jpaRepository.saveAll(cuentaMovimientos.stream()
                .map(mapper::toEntity)
                .collect(Collectors.toList())).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAllByFechaAndOrden(OffsetDateTime fechaContable, Integer ordenContable) {
        jpaRepository.deleteAllByFechaAndOrden(fechaContable, ordenContable);
    }

}
