package eterea.core.service.hexagonal.reservas.voucher.domain.ports.in;

import eterea.core.service.hexagonal.reservas.voucher.domain.model.Voucher;

import java.util.Optional;

public interface FindVoucherByNumeroVoucherAlreadyRegisteredUseCase {

    Optional<Voucher> findByNumeroVoucherAlreadyRegistered(String numeroVoucher);

}
