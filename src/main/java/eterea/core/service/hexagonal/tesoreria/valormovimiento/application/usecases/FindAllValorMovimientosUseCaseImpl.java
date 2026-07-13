package eterea.core.service.hexagonal.tesoreria.valormovimiento.application.usecases;

import eterea.core.service.hexagonal.tesoreria.valormovimiento.domain.model.ValorMovimiento;
import eterea.core.service.hexagonal.tesoreria.valormovimiento.domain.ports.in.FindAllValorMovimientosUseCase;
import eterea.core.service.hexagonal.tesoreria.valormovimiento.domain.ports.out.ValorMovimientoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Component
@Slf4j
public class FindAllValorMovimientosUseCaseImpl implements FindAllValorMovimientosUseCase {

    private final ValorMovimientoRepository repository;

    public FindAllValorMovimientosUseCaseImpl(ValorMovimientoRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ValorMovimiento> findAll(LocalDate desde, LocalDate hasta, boolean cierreCajaOnly, boolean ingresosOnly) {
        log.debug("Processing FindAllValorMovimientosUseCaseImpl.findAll");
        return repository.findAllByFechaContableBetween(
                OffsetDateTime.of(desde.atTime(LocalTime.MIN), ZoneOffset.UTC),
                OffsetDateTime.of(hasta.atTime(LocalTime.MIN), ZoneOffset.UTC),
                cierreCajaOnly,
                ingresosOnly);
    }
}
