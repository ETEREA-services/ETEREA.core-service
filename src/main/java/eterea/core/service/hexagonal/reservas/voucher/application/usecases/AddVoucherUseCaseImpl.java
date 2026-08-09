package eterea.core.service.hexagonal.reservas.voucher.application.usecases;

import eterea.core.service.hexagonal.reservas.voucher.domain.model.Voucher;
import eterea.core.service.hexagonal.reservas.voucher.domain.ports.in.AddVoucherUseCase;
import eterea.core.service.hexagonal.reservas.voucher.domain.ports.out.VoucherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AddVoucherUseCaseImpl implements AddVoucherUseCase {

    private final VoucherRepository voucherRepository;

    @Override
    public Voucher add(Voucher voucher) {
        return voucherRepository.save(voucher);
    }

}
