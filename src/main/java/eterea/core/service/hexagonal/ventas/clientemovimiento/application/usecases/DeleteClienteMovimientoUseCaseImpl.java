package eterea.core.service.hexagonal.ventas.clientemovimiento.application.usecases;

import eterea.core.service.hexagonal.ventas.clientemovimiento.domain.ports.in.DeleteClienteMovimientoUseCase;
import eterea.core.service.hexagonal.ventas.clientemovimiento.domain.ports.out.ClienteMovimientoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;

@Component
@RequiredArgsConstructor
public class DeleteClienteMovimientoUseCaseImpl implements DeleteClienteMovimientoUseCase {

    private final ClienteMovimientoRepository repository;

    @Override
    @Transactional
    public void deleteAll0ByFecha(OffsetDateTime fecha) {
        repository.deleteAllByFechaComprobanteAndComprobanteIdAndPuntoVentaAndNumeroComprobante(fecha, 0, 0, 0L);
    }
}
