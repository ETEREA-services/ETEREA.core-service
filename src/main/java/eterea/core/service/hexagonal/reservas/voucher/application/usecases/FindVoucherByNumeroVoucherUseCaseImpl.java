package eterea.core.service.hexagonal.reservas.voucher.application.usecases;

import eterea.core.service.hexagonal.reservas.voucher.domain.model.Voucher;
import eterea.core.service.hexagonal.reservas.voucher.domain.ports.in.FindVoucherByNumeroVoucherUseCase;
import eterea.core.service.hexagonal.reservas.voucher.domain.ports.out.VoucherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FindVoucherByNumeroVoucherUseCaseImpl implements FindVoucherByNumeroVoucherUseCase {

    private final VoucherRepository voucherRepository;

    @Override
    public Optional<Voucher> findByNumeroVoucher(String numeroVoucher) {
        return voucherRepository.findTopByNumeroVoucherContains(numeroVoucher);
    }

}
