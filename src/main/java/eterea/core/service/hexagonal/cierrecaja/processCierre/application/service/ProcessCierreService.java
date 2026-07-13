package eterea.core.service.hexagonal.cierrecaja.processCierre.application.service;

import eterea.core.service.hexagonal.cierrecaja.processCierre.domain.model.PendingCounts;
import eterea.core.service.hexagonal.cierrecaja.processCierre.domain.ports.in.GetPendingCountsUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
@RequiredArgsConstructor
public class ProcessCierreService {

    private final GetPendingCountsUseCase getPendingCountsUseCase;

    public PendingCounts getPendingCounts(OffsetDateTime desde, OffsetDateTime hasta) {
        return getPendingCountsUseCase.getPendingCounts(desde, hasta);
    }

}
