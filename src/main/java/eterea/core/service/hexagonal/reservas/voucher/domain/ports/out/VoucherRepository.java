package eterea.core.service.hexagonal.reservas.voucher.domain.ports.out;

import eterea.core.service.hexagonal.reservas.voucher.domain.model.Voucher;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

public interface VoucherRepository {

    List<Voucher> findAllByFechaVencimientoAndUsuario(OffsetDateTime fechaVencimiento, String usuario);

    List<Voucher> findAllByFechaServicio(OffsetDateTime fechaServicio, boolean porNombrePax);

    Optional<Voucher> findByReservaId(Long reservaId);

    Optional<Voucher> findByVoucherId(Long voucherId);

    Optional<Voucher> findTopByNumeroVoucherContains(String numeroVoucher);

    Optional<Voucher> findTopByNumeroVoucherContainsAndFechaTomaAfter(String numeroVoucher, OffsetDateTime fechaToma);

    Voucher save(Voucher voucher);

}
