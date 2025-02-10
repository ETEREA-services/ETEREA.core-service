package eterea.core.service.model.dto.programadia;

import java.time.OffsetDateTime;
import java.util.List;

public record ProgramaDiaDetallesDto(
   OffsetDateTime fechaServicio,
   VoucherDto voucher,
   ReservaDto reserva,
   ProveedorDto proveedor,
   List<VentasPorGrupoDto> ventasPorGrupo
) {
    
}
