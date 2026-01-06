package eterea.core.service.hexagonal.proveedormovimiento.domain.ports.out;

import eterea.core.service.hexagonal.proveedormovimiento.domain.model.ProveedorMovimiento;
import eterea.core.service.hexagonal.proveedormovimiento.domain.model.ResumenIvaComprasMensual;
import org.springframework.data.domain.Sort;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

public interface ProveedorMovimientoRepository {

    List<ProveedorMovimiento> findAllByProveedorId(Long proveedorId);

    List<ProveedorMovimiento> findAllByFechaContableBetweenAndComprobanteLibroIva(OffsetDateTime desde, OffsetDateTime hasta, Byte libroIva, Sort sort);

    Optional<ProveedorMovimiento> findByProveedorMovimientoId(Long proveedorMovimientoId);

    ProveedorMovimiento save(ProveedorMovimiento proveedorMovimiento);

    ResumenIvaComprasMensual findResumenByYearAndMonth(Integer anho, Integer mes);

}
