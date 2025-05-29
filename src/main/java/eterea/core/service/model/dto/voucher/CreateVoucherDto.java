package eterea.core.service.model.dto.voucher;

import java.util.List;

import eterea.core.service.kotlin.model.Voucher;
import eterea.core.service.kotlin.model.VoucherProducto;

public record CreateVoucherDto(
   Voucher voucher,
   List<VoucherProducto> voucherProductos,
   String clienteNumeroDocumento
) {
   
}
