package eterea.core.service.hexagonal.reservas.voucher.domain.ports.in;

import eterea.core.service.hexagonal.reservas.voucher.domain.model.Voucher;

public interface AddVoucherUseCase {

    Voucher add(Voucher voucher);

}
