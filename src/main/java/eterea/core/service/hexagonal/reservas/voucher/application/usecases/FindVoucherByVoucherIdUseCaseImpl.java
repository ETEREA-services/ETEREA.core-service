package eterea.core.service.hexagonal.reservas.voucher.application.usecases;

import eterea.core.service.hexagonal.reservas.voucher.domain.model.Voucher;
import eterea.core.service.hexagonal.reservas.voucher.domain.ports.in.FindVoucherByVoucherIdUseCase;
import eterea.core.service.hexagonal.reservas.voucher.domain.ports.out.VoucherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FindVoucherByVoucherIdUseCaseImpl implements FindVoucherByVoucherIdUseCase {

    private final VoucherRepository voucherRepository;

    @Override
    public Optional<Voucher> findByVoucherId(Long voucherId) {
        return voucherRepository.findByVoucherId(voucherId);
    }

}
