package eterea.core.service.hexagonal.articulomovimiento.application.usecases;

import eterea.core.service.hexagonal.articulomovimiento.domain.model.ArticuloMovimiento;
import eterea.core.service.hexagonal.articulomovimiento.domain.ports.in.AutoCompleteTotalesByClienteMovimientoIdUseCase;
import eterea.core.service.hexagonal.articulomovimiento.domain.ports.out.ArticuloMovimientoRepository;
import eterea.core.service.tool.Jsonifier;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class AutoCompleteTotalesByClienteMovimientoIdUseCaseImpl implements AutoCompleteTotalesByClienteMovimientoIdUseCase {

    private final ArticuloMovimientoRepository repository;

    @Override
    public List<ArticuloMovimiento> calculateTotalesByClienteMovimientoId(Long clienteMovimientoId) {
        log.debug("\n\nProcessing AutoCompleteTotalesByClienteMovimientoIdUseCaseImpl.calculateTotalesByClienteMovimientoId\n\n");
        List<ArticuloMovimiento> movimientos = repository.findAllByClienteMovimientoId(clienteMovimientoId);
        log.debug("\n\nArticuloMovimientos -> {}\n\n", Jsonifier.builder(movimientos).build());
        movimientos.forEach(movimiento -> {
            var tasaImpuesto = movimiento.getPrecioUnitarioConIva().divide(movimiento.getPrecioUnitarioSinIva(), 3, RoundingMode.HALF_UP);
            if (movimiento.getTotalConIva().compareTo(BigDecimal.ZERO) == 0) {
                movimiento.setTotalConIva(movimiento.getPrecioUnitarioConIva().multiply(movimiento.getCantidad().abs()));
            }
            if (movimiento.getTotalSinIva().compareTo(BigDecimal.ZERO) == 0) {
                movimiento.setTotalSinIva(movimiento.getTotalConIva().divide(tasaImpuesto, 4, RoundingMode.HALF_UP));
            }
            log.debug("\n\nArticuloMovimiento -> {}\n\n", movimiento.jsonify());
        });
        log.debug("\n\nArticuloMovimientos updated -> {}\n\n", Jsonifier.builder(movimientos).build());
        movimientos = repository.saveAll(movimientos);
        return movimientos;
    }

}
