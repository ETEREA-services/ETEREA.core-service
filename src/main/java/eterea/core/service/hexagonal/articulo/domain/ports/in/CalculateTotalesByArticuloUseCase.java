package eterea.core.service.hexagonal.articulo.domain.ports.in;

import eterea.core.service.hexagonal.articulo.infrastructure.web.dto.TotalArticuloResponse;
import java.math.BigDecimal;

public interface CalculateTotalesByArticuloUseCase {

    TotalArticuloResponse calculateTotales(String articuloId, BigDecimal cantidad, BigDecimal precioUnitarioConIva);

}
