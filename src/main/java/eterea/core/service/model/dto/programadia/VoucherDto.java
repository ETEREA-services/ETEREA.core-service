package eterea.core.service.model.dto.programadia;

import java.util.List;

public record VoucherDto(
   Long voucherId,
   String nombrePax,
   Integer cantidadPax,
   String subeEn,
   String observaciones,
   Boolean confirmado,
   Boolean pagaCacheuta,
   String contacto,
   Integer paxsReales,
   Long reservaId,
   String numeroVoucher

) {

}
