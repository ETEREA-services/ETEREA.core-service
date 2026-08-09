package eterea.core.service.hexagonal.reservas.voucher.application.usecases;

import eterea.core.service.hexagonal.reservas.voucher.domain.model.Voucher;
import eterea.core.service.hexagonal.reservas.voucher.domain.ports.in.FindAllVoucherByFechaServicioUseCase;
import eterea.core.service.hexagonal.reservas.voucher.domain.ports.out.VoucherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class FindAllVoucherByFechaServicioUseCaseImpl implements FindAllVoucherByFechaServicioUseCase {

    private final VoucherRepository voucherRepository;

    @Override
    public List<Voucher> findAllByFechaServicio(OffsetDateTime fechaServicio, Boolean soloConfirmados,
            Boolean porNombrePax) {
        List<Voucher> vouchers = voucherRepository.findAllByFechaServicio(fechaServicio,
                Boolean.TRUE.equals(porNombrePax));
        if (Boolean.TRUE.equals(soloConfirmados)) {
            return vouchers.stream()
                    .filter(voucher -> voucher.getConfirmado() != null && voucher.getConfirmado() == 1)
                    .toList();
        }
        return vouchers;
    }

}
