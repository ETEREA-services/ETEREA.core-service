package eterea.core.service.hexagonal.proveedormovimiento.domain.ports.in;

import eterea.core.service.hexagonal.proveedormovimiento.domain.model.ProveedorMovimiento;
import eterea.core.service.hexagonal.proveedormovimiento.infrastructure.web.dto.ProveedorMovimientoNetoAjusteRequest;

public interface UpdateProveedorMovimientoNetoAjusteUseCase {
    ProveedorMovimiento updateProveedorMovimientoNetoAjuste(ProveedorMovimientoNetoAjusteRequest request);
}
