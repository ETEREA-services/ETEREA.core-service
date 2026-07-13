package eterea.core.service.hexagonal.compras.proveedormovimiento.domain.ports.in;

import eterea.core.service.hexagonal.compras.proveedormovimiento.domain.model.ProveedorMovimiento;

import java.util.List;

public interface GetProveedorMovimientosByProveedorIdUseCase {
    List<ProveedorMovimiento> getProveedorMovimientosByProveedorId(Long proveedorId);
}
