package eterea.core.service.hexagonal.reservas.voucher.application.usecases;

import eterea.core.service.hexagonal.reservas.voucher.domain.model.Voucher;
import eterea.core.service.hexagonal.reservas.voucher.domain.ports.in.AddVoucherUseCase;
import eterea.core.service.hexagonal.reservas.voucher.domain.ports.in.SaveVoucherUseCase;
import eterea.core.service.hexagonal.reservas.voucher.domain.ports.in.UpdateVoucherUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SaveVoucherUseCaseImpl implements SaveVoucherUseCase {

    private final AddVoucherUseCase addVoucherUseCase;
    private final UpdateVoucherUseCase updateVoucherUseCase;

    @Override
    public Voucher save(Voucher voucher, String trackUuid) {
        if (trackUuid != null) {
            voucher.setTrackUuid(trackUuid);
        }
        if (voucher.getVoucherId() == null) {
            return addVoucherUseCase.add(voucher);
        }
        return updateVoucherUseCase.update(voucher, voucher.getVoucherId());
    }

}
