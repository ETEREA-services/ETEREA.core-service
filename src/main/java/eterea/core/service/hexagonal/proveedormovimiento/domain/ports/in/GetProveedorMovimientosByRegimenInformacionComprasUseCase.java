package eterea.core.service.hexagonal.proveedormovimiento.domain.ports.in;

import eterea.core.service.hexagonal.proveedormovimiento.domain.model.ProveedorMovimiento;

import java.time.OffsetDateTime;
import java.util.List;

public interface GetProveedorMovimientosByRegimenInformacionComprasUseCase {
    List<ProveedorMovimiento> getProveedorMovimientosByRegimenInformacionCompras(OffsetDateTime desde, OffsetDateTime hasta);
}
