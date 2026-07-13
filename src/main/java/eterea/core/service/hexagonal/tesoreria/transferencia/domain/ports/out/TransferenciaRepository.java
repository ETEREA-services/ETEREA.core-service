package eterea.core.service.hexagonal.tesoreria.transferencia.domain.ports.out;

import eterea.core.service.hexagonal.tesoreria.transferencia.domain.model.Transferencia;

import java.util.Optional;

public interface TransferenciaRepository {

    Optional<Transferencia> findByNegocioIdDesdeAndNegocioIdHastaAndNumeroTransferencia(
            Integer negocioIdDesde,
            Integer negocioIdHasta,
            Long numeroTransferencia
    );

}
