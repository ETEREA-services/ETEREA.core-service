package eterea.core.service.hexagonal.reservas.voucher.domain.ports.in;

import eterea.core.service.hexagonal.reservas.voucher.domain.model.Voucher;

import java.time.OffsetDateTime;
import java.util.List;

public interface FindAllVoucherByUserTodayUseCase {

    List<Voucher> findAllByUserToday(OffsetDateTime fechaVencimiento, String usuario);

}
