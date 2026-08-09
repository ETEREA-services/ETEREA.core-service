package eterea.core.service.hexagonal.reservas.voucher.application.usecases;

import eterea.core.service.hexagonal.reservas.voucher.domain.model.Voucher;
import eterea.core.service.hexagonal.reservas.voucher.domain.ports.in.FindVoucherByReservaIdUseCase;
import eterea.core.service.hexagonal.reservas.voucher.domain.ports.out.VoucherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FindVoucherByReservaIdUseCaseImpl implements FindVoucherByReservaIdUseCase {

    private final VoucherRepository voucherRepository;

    @Override
    public Optional<Voucher> findByReservaId(Long reservaId) {
        return voucherRepository.findByReservaId(reservaId);
    }

}
