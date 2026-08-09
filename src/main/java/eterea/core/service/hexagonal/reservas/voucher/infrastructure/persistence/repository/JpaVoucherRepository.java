package eterea.core.service.hexagonal.reservas.voucher.infrastructure.persistence.repository;

import eterea.core.service.hexagonal.reservas.voucher.infrastructure.persistence.entity.VoucherEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface JpaVoucherRepository extends JpaRepository<VoucherEntity, Long> {

    List<VoucherEntity> findAllByFechaVencimientoAndUsuario(OffsetDateTime fechaVencimiento, String usuario);

    List<VoucherEntity> findAllByFechaServicio(OffsetDateTime fechaServicio, Sort sort);

    Optional<VoucherEntity> findByReservaId(Long reservaId);

    Optional<VoucherEntity> findByVoucherId(Long voucherId);

    Optional<VoucherEntity> findTopByNumeroVoucherContains(String numeroVoucher);

    Optional<VoucherEntity> findTopByNumeroVoucherContainsAndFechaTomaAfter(String numeroVoucher,
            OffsetDateTime fechaToma);

}
