package eterea.core.service.hexagonal.transferencia.application.usecases;

import eterea.core.service.kotlin.exception.TransferenciaException;
import eterea.core.service.hexagonal.transferencia.domain.model.Transferencia;
import eterea.core.service.hexagonal.transferencia.domain.ports.in.FindTransferenciaByUniqueUseCase;
import eterea.core.service.hexagonal.transferencia.domain.ports.out.TransferenciaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class FindTransferenciaByUniqueUseCaseImpl implements FindTransferenciaByUniqueUseCase {

    private final TransferenciaRepository repository;

    public FindTransferenciaByUniqueUseCaseImpl(TransferenciaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Transferencia findByUnique(Integer negocioIdDesde, Integer negocioIdHasta, Long numeroTransferencia) {
        log.debug("Processing FindTransferenciaByUniqueUseCaseImpl.findByUnique() with parameters: negocioIdDesde: {}, negocioIdHasta: {}, numeroTransferencia: {}", negocioIdDesde, negocioIdHasta, numeroTransferencia);
        return repository.findByNegocioIdDesdeAndNegocioIdHastaAndNumeroTransferencia(negocioIdDesde, negocioIdHasta, numeroTransferencia)
                .orElseThrow(() -> new TransferenciaException(negocioIdDesde, negocioIdHasta, numeroTransferencia));
    }
}
