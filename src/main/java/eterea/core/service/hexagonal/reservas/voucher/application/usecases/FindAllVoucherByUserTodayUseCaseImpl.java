package eterea.core.service.hexagonal.reservas.voucher.application.usecases;

import eterea.core.service.hexagonal.reservas.voucher.domain.model.Voucher;
import eterea.core.service.hexagonal.reservas.voucher.domain.ports.in.FindAllVoucherByUserTodayUseCase;
import eterea.core.service.hexagonal.reservas.voucher.domain.ports.out.VoucherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class FindAllVoucherByUserTodayUseCaseImpl implements FindAllVoucherByUserTodayUseCase {

    private final VoucherRepository voucherRepository;

    @Override
    public List<Voucher> findAllByUserToday(OffsetDateTime fechaVencimiento, String usuario) {
        return voucherRepository.findAllByFechaVencimientoAndUsuario(fechaVencimiento, usuario);
    }

}
