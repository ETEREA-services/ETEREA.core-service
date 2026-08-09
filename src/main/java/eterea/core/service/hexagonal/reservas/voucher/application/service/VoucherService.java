package eterea.core.service.hexagonal.reservas.voucher.application.service;

import eterea.core.service.hexagonal.reservas.voucher.application.exception.VoucherException;
import eterea.core.service.hexagonal.reservas.voucher.domain.model.Voucher;
import eterea.core.service.hexagonal.reservas.voucher.domain.ports.in.AddVoucherUseCase;
import eterea.core.service.hexagonal.reservas.voucher.domain.ports.in.FindAllVoucherByFechaServicioUseCase;
import eterea.core.service.hexagonal.reservas.voucher.domain.ports.in.FindAllVoucherByUserTodayUseCase;
import eterea.core.service.hexagonal.reservas.voucher.domain.ports.in.FindVoucherByNumeroVoucherAlreadyRegisteredUseCase;
import eterea.core.service.hexagonal.reservas.voucher.domain.ports.in.FindVoucherByNumeroVoucherUseCase;
import eterea.core.service.hexagonal.reservas.voucher.domain.ports.in.FindVoucherByReservaIdUseCase;
import eterea.core.service.hexagonal.reservas.voucher.domain.ports.in.FindVoucherByVoucherIdUseCase;
import eterea.core.service.hexagonal.reservas.voucher.domain.ports.in.SaveVoucherUseCase;
import eterea.core.service.hexagonal.reservas.voucher.domain.ports.in.UpdateVoucherUseCase;
import eterea.core.service.model.Track;
import eterea.core.service.tool.ToolService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class VoucherService {

    private final FindAllVoucherByUserTodayUseCase findAllVoucherByUserTodayUseCase;
    private final FindAllVoucherByFechaServicioUseCase findAllVoucherByFechaServicioUseCase;
    private final FindVoucherByReservaIdUseCase findVoucherByReservaIdUseCase;
    private final FindVoucherByVoucherIdUseCase findVoucherByVoucherIdUseCase;
    private final FindVoucherByNumeroVoucherUseCase findVoucherByNumeroVoucherUseCase;
    private final FindVoucherByNumeroVoucherAlreadyRegisteredUseCase findVoucherByNumeroVoucherAlreadyRegisteredUseCase;
    private final SaveVoucherUseCase saveVoucherUseCase;
    private final AddVoucherUseCase addVoucherUseCase;
    private final UpdateVoucherUseCase updateVoucherUseCase;

    public List<Voucher> findAllByUserToday(String login) {
        return findAllVoucherByUserTodayUseCase.findAllByUserToday(ToolService.dateAbsoluteArgentina(), login);
    }

    public List<Voucher> findAllByFechaServicio(OffsetDateTime fechaServicio, Boolean soloConfirmados,
            Boolean porNombrePax) {
        return findAllVoucherByFechaServicioUseCase.findAllByFechaServicio(fechaServicio, soloConfirmados,
                porNombrePax);
    }

    public Voucher findByReservaId(Long reservaId) {
        return findVoucherByReservaIdUseCase.findByReservaId(reservaId)
                .orElseThrow(() -> new VoucherException(reservaId, "Reserva"));
    }

    public Voucher findByVoucherId(Long voucherId) {
        return findVoucherByVoucherIdUseCase.findByVoucherId(voucherId)
                .orElseThrow(() -> new VoucherException(voucherId, "Programa por el Día"));
    }

    public Voucher findByNumeroVoucher(String numeroVoucher) {
        return findVoucherByNumeroVoucherUseCase.findByNumeroVoucher(numeroVoucher)
                .orElseThrow(() -> new VoucherException(numeroVoucher));
    }

    public Voucher findByNumeroVoucherAlreadyRegistered(String numeroVoucher) {
        log.debug("Processing findByNumeroVoucherAlreadyRegistered");
        return findVoucherByNumeroVoucherAlreadyRegisteredUseCase.findByNumeroVoucherAlreadyRegistered(numeroVoucher)
                .orElseThrow(() -> new VoucherException(numeroVoucher));
    }

    public Voucher save(Voucher voucher, Track track) {
        return saveVoucherUseCase.save(voucher, track != null ? track.getUuid() : null);
    }

    public Voucher add(Voucher voucher) {
        return addVoucherUseCase.add(voucher);
    }

    public Voucher update(Voucher newVoucher, Long voucherId) {
        return updateVoucherUseCase.update(newVoucher, voucherId);
    }

}
