package eterea.core.service.hexagonal.tesoreria.valormovimiento.domain.ports.in;

import eterea.core.service.hexagonal.tesoreria.valormovimiento.domain.model.ValorMovimiento;

import java.time.LocalDate;
import java.util.List;

public interface FindAllValorMovimientosUseCase {
    List<ValorMovimiento> findAll(LocalDate desde, LocalDate hasta, boolean cierreCajaOnly, boolean ingresosOnly);
}
