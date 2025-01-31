package eterea.core.service.model.dto.programadia;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

public record VentasPorGrupoDto(OffsetDateTime fechaServicio, String grupoNombre, BigDecimal totalVentas, List<VentasPorGrupoPorProveedor> ventasPorProveedor) {
   
}
