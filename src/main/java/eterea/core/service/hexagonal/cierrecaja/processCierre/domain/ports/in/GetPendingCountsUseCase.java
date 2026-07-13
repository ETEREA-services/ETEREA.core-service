package eterea.core.service.hexagonal.cierrecaja.processCierre.domain.ports.in;

import eterea.core.service.hexagonal.cierrecaja.processCierre.domain.model.PendingCounts;
import java.time.OffsetDateTime;

public interface GetPendingCountsUseCase {

    PendingCounts getPendingCounts(OffsetDateTime desde, OffsetDateTime hasta);

}
