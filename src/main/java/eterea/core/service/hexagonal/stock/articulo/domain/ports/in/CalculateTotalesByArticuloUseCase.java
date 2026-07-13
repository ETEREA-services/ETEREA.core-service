package eterea.core.service.hexagonal.stock.articulo.domain.ports.in;

import eterea.core.service.hexagonal.stock.articulo.infrastructure.web.dto.TotalArticuloResponse;
import java.math.BigDecimal;

public interface CalculateTotalesByArticuloUseCase {

    TotalArticuloResponse calculateTotales(String articuloId, BigDecimal cantidad, BigDecimal precioUnitarioConIva);

}
