package eterea.core.service.hexagonal.compras.proveedormovimiento.domain.ports.in;

import eterea.core.service.hexagonal.compras.proveedormovimiento.domain.model.ResumenIvaComprasMensual;

public interface GetResumenIvaComprasMensualUseCase {

    ResumenIvaComprasMensual getResumenIvaComprasMensual(Integer anho, Integer mes);

}
