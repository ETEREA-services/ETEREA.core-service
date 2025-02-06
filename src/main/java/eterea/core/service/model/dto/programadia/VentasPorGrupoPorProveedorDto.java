package eterea.core.service.model.dto.programadia;

import java.math.BigDecimal;

public record VentasPorGrupoPorProveedorDto(String proveedorNombre, BigDecimal cantidad,
      ProgramaDiaProductoDto producto) {

}
