package eterea.core.service.hexagonal.ventas.clientemovimiento.domain.ports.in;

import java.time.OffsetDateTime;

public interface DeleteClienteMovimientoUseCase {
    void deleteAll0ByFecha(OffsetDateTime fecha);
}
