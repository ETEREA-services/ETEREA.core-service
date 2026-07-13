package eterea.core.service.hexagonal.cierrecaja.processCierre.domain.ports.out;

import eterea.core.service.hexagonal.cierrecaja.processCierre.domain.model.PendingCounts;
import java.time.OffsetDateTime;

public interface ProcessCierreRepository {

    PendingCounts getPendingCounts(OffsetDateTime desde, OffsetDateTime hasta);

}
