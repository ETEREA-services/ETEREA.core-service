package eterea.core.service.hexagonal.proveedormovimiento.domain.ports.in;

import eterea.core.service.hexagonal.proveedormovimiento.domain.model.ResumenIvaComprasMensual;

public interface GetResumenIvaComprasMensualUseCase {

    ResumenIvaComprasMensual getResumenIvaComprasMensual(Integer anho, Integer mes);

}
