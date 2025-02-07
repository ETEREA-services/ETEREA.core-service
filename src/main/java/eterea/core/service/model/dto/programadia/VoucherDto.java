package eterea.core.service.model.dto.programadia;

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
