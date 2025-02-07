package eterea.core.service.model.dto.programadia;

import java.math.BigDecimal;

public record ProgramaDiaVentasProveedorDto(
   String proveedorNombre,
   Integer productoId,
   BigDecimal cantidad
) {
   
}
