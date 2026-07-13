package eterea.core.service.hexagonal.tesoreria.transferencia.domain.ports.in;

import eterea.core.service.hexagonal.tesoreria.transferencia.domain.model.Transferencia;

public interface FindTransferenciaByUniqueUseCase {

    Transferencia findByUnique(Integer negocioIdDesde, Integer negocioIdHasta, Long numeroTransferencia);

}
