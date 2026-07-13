package eterea.core.service.hexagonal.compras.proveedormovimiento.domain.ports.in;

import eterea.core.service.hexagonal.compras.proveedormovimiento.domain.model.ProveedorMovimiento;
import eterea.core.service.hexagonal.compras.proveedormovimiento.infrastructure.web.dto.ProveedorMovimientoNetoAjusteRequest;

public interface UpdateProveedorMovimientoNetoAjusteUseCase {
    ProveedorMovimiento updateProveedorMovimientoNetoAjuste(ProveedorMovimientoNetoAjusteRequest request);
}
