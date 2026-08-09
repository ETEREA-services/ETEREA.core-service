package eterea.core.service.hexagonal.reservas.voucher.application.usecases;

import eterea.core.service.hexagonal.reservas.voucher.domain.model.Voucher;
import eterea.core.service.hexagonal.reservas.voucher.domain.ports.in.FindVoucherByNumeroVoucherAlreadyRegisteredUseCase;
import eterea.core.service.hexagonal.reservas.voucher.domain.ports.out.VoucherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FindVoucherByNumeroVoucherAlreadyRegisteredUseCaseImpl
        implements FindVoucherByNumeroVoucherAlreadyRegisteredUseCase {

    private final VoucherRepository voucherRepository;

    @Override
    public Optional<Voucher> findByNumeroVoucherAlreadyRegistered(String numeroVoucher) {
        return voucherRepository.findTopByNumeroVoucherContainsAndFechaTomaAfter(numeroVoucher,
                OffsetDateTime.of(2024, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC));
    }

}
