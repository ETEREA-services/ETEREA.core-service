package eterea.core.service.hexagonal.cierrecaja.processCierre.application.usecases;

import eterea.core.service.hexagonal.cierrecaja.processCierre.domain.model.PendingCounts;
import eterea.core.service.hexagonal.cierrecaja.processCierre.domain.ports.in.GetPendingCountsUseCase;
import eterea.core.service.hexagonal.cierrecaja.processCierre.domain.ports.out.ProcessCierreRepository;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;

@Component
public class GetPendingCountsUseCaseImpl implements GetPendingCountsUseCase {

    private final ProcessCierreRepository repository;

    public GetPendingCountsUseCaseImpl(ProcessCierreRepository repository) {
        this.repository = repository;
    }

    @Override
    public PendingCounts getPendingCounts(OffsetDateTime desde, OffsetDateTime hasta) {
        return repository.getPendingCounts(desde, hasta);
    }

}
