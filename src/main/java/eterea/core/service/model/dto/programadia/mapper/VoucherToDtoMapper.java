package eterea.core.service.model.dto.programadia.mapper;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import eterea.core.service.kotlin.model.Voucher;
import eterea.core.service.model.dto.programadia.VoucherDto;

@Component
public class VoucherToDtoMapper implements Function<Voucher, VoucherDto> {
   
   @Override
   public VoucherDto apply(Voucher voucher) {
      return new VoucherDto(
         voucher.getVoucherId(),
         voucher.getNombrePax(),
         voucher.getCantidadPax(),
         voucher.getSubeEn(),
         voucher.getObservaciones(),
         voucher.getConfirmado() == 1,
         voucher.getPagaCacheuta() == 1,
         voucher.getContacto(),
         voucher.getPaxsReales(),
         voucher.getReservaId(),
         voucher.getNumeroVoucher()
      );
   }
}
