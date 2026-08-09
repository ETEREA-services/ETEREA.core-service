package eterea.core.service.hexagonal.reservas.voucher.domain.ports.in;

import eterea.core.service.hexagonal.reservas.voucher.domain.model.Voucher;

public interface UpdateVoucherUseCase {

    Voucher update(Voucher newVoucher, Long voucherId);

}
