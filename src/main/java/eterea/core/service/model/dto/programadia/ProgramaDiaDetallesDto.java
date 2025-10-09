package eterea.core.service.model.dto.programadia;

import java.time.OffsetDateTime;
import java.util.List;

public record ProgramaDiaDetallesDto(
   OffsetDateTime fechaServicio,
   VoucherDto voucher,
   ReservaDto reserva,
   String razonSocial,
   ProveedorDto proveedor,
   List<VentasPorGrupoDto> ventasPorGrupo
) {
    
}
