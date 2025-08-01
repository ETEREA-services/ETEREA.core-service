package eterea.core.service.model.dto.pluspagos;

import java.math.BigDecimal;

public record PluspagosProductoTransaccionDto(
      String nombreProducto,
      BigDecimal montoProducto) {

}
