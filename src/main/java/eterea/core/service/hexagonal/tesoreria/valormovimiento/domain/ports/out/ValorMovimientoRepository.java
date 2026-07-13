package eterea.core.service.hexagonal.tesoreria.valormovimiento.domain.ports.out;

import eterea.core.service.hexagonal.tesoreria.valormovimiento.domain.model.ValorMovimiento;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

public interface ValorMovimientoRepository {

    Optional<ValorMovimiento> findByValorMovimientoId(Long valorMovimientoId);

    List<ValorMovimiento> findAllByFechaContableBetween(
            OffsetDateTime desde,
            OffsetDateTime hasta,
            Boolean cierreCajaOnly,
            Boolean ingresosOnly);

    List<ValorMovimiento> findAllByFechaContableAndOrdenContable(
            OffsetDateTime fechaContable,
            Integer ordenContable);

    void deleteAllByFechaContableAndOrdenContable(OffsetDateTime fechaContable, Integer ordenContable);

    ValorMovimiento save(ValorMovimiento valorMovimiento);

    List<ValorMovimiento> saveAll(List<ValorMovimiento> valorMovimientos);

}
