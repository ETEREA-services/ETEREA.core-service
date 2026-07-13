package eterea.core.service.hexagonal.tesoreria.valormovimiento.application.usecases;

import eterea.core.service.hexagonal.tesoreria.valormovimiento.domain.ports.in.DeleteValorMovimientosByContableUseCase;
import eterea.core.service.hexagonal.tesoreria.valormovimiento.domain.ports.out.ValorMovimientoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;

@Component
@Slf4j
public class DeleteValorMovimientosByContableUseCaseImpl implements DeleteValorMovimientosByContableUseCase {

    private final ValorMovimientoRepository repository;

    public DeleteValorMovimientosByContableUseCaseImpl(ValorMovimientoRepository repository) {
        this.repository = repository;
    }

    @Override
    public void deleteByContable(OffsetDateTime fechaContable, Integer ordenContable) {
        log.debug("Processing DeleteValorMovimientosByContableUseCaseImpl.deleteByContable fecha={} orden={}", fechaContable, ordenContable);
        repository.deleteAllByFechaContableAndOrdenContable(fechaContable, ordenContable);
    }
}
