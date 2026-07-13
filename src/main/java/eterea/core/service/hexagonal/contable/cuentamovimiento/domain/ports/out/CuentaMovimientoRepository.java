package eterea.core.service.hexagonal.contable.cuentamovimiento.domain.ports.out;

import eterea.core.service.hexagonal.contable.cuentamovimiento.domain.model.CuentaMovimiento;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

public interface CuentaMovimientoRepository {

    List<CuentaMovimiento> findAllByFechaAndOrden(OffsetDateTime fechaContable, Integer ordenContable);

    List<CuentaMovimiento> findAllByFechaBetween(OffsetDateTime fechaDesde, OffsetDateTime fechaHasta);

    Optional<CuentaMovimiento> findByCuentaMovimientoId(Long cuentaMovimientoId);

    Optional<CuentaMovimiento> findFirstByFechaAndOrdenOrderByItemDesc(OffsetDateTime fecha, Integer orden);

    Optional<CuentaMovimiento> findFirstByFechaOrderByOrdenDesc(OffsetDateTime fecha);

    BigDecimal calculateTotalByNumeroCuentaAndDebitaAndFechaBetween(Long numeroCuenta, Integer debita, OffsetDateTime desde, OffsetDateTime hasta);

    BigDecimal calculateTotalByNumeroCuentaAndDebitaAndInflacionAndFechaBetween(Long numeroCuenta, Integer debita, Integer inflacion, OffsetDateTime desde, OffsetDateTime hasta);

    List<CuentaMovimiento> saveAll(List<CuentaMovimiento> cuentaMovimientos);

    void deleteAllByFechaAndOrden(OffsetDateTime fechaContable, Integer ordenContable);

}
