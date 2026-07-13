package eterea.core.service.hexagonal.tesoreria.valormovimiento.application.service;

import eterea.core.service.hexagonal.tesoreria.valormovimiento.domain.model.ValorMovimiento;
import eterea.core.service.hexagonal.tesoreria.valormovimiento.domain.ports.in.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ValorMovimientoService {

    private final FindValorMovimientosByContableUseCase findValorMovimientosByContableUseCase;
    private final CreateValorMovimientoUseCase createValorMovimientoUseCase;
    private final UpdateValorMovimientoUseCase updateValorMovimientoUseCase;
    private final FindAllValorMovimientosUseCase findAllValorMovimientosUseCase;
    private final DeleteValorMovimientosByContableUseCase deleteValorMovimientosByContableUseCase;
    private final SaveAllValorMovimientosUseCase saveAllValorMovimientosUseCase;

    public List<ValorMovimiento> findAllByContable(OffsetDateTime fechaContable, Integer ordenContable) {
        return findValorMovimientosByContableUseCase.findByContable(fechaContable, ordenContable);
    }

    public ValorMovimiento add(ValorMovimiento valorMovimiento) {
        return createValorMovimientoUseCase.create(valorMovimiento);
    }

    public ValorMovimiento update(ValorMovimiento newValorMovimiento, Long valorMovimientoId) {
        return updateValorMovimientoUseCase.update(newValorMovimiento, valorMovimientoId);
    }

    public List<ValorMovimiento> findAllMovimientos(LocalDate desde, LocalDate hasta,
                                                     boolean cierreCajaOnly, boolean ingresosOnly) {
        return findAllValorMovimientosUseCase.findAll(desde, hasta, cierreCajaOnly, ingresosOnly);
    }

    public void deleteAllByContable(OffsetDateTime fechaContable, Integer ordenContable) {
        deleteValorMovimientosByContableUseCase.deleteByContable(fechaContable, ordenContable);
    }

    public List<ValorMovimiento> saveAll(List<ValorMovimiento> valorMovimientos) {
        return saveAllValorMovimientosUseCase.saveAll(valorMovimientos);
    }

}
