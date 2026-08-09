package eterea.core.service.hexagonal.reservas.voucher.infrastructure.persistence.adapter;

import eterea.core.service.hexagonal.reservas.voucher.domain.model.Voucher;
import eterea.core.service.hexagonal.reservas.voucher.domain.ports.out.VoucherRepository;
import eterea.core.service.hexagonal.reservas.voucher.infrastructure.persistence.mapper.VoucherMapper;
import eterea.core.service.hexagonal.reservas.voucher.infrastructure.persistence.repository.JpaVoucherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JpaVoucherRepositoryAdapter implements VoucherRepository {

    private final JpaVoucherRepository jpaRepository;
    private final VoucherMapper voucherMapper;

    @Override
    public List<Voucher> findAllByFechaVencimientoAndUsuario(OffsetDateTime fechaVencimiento, String usuario) {
        return jpaRepository.findAllByFechaVencimientoAndUsuario(fechaVencimiento, usuario).stream()
                .map(voucherMapper::toDomain)
                .toList();
    }

    @Override
    public List<Voucher> findAllByFechaServicio(OffsetDateTime fechaServicio, boolean porNombrePax) {
        Sort sort = porNombrePax
                ? Sort.by("nombrePax").ascending()
                : Sort.by("cliente.razonSocial").ascending().and(Sort.by("nombrePax").ascending());
        return jpaRepository.findAllByFechaServicio(fechaServicio, sort).stream()
                .map(voucherMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Voucher> findByReservaId(Long reservaId) {
        return jpaRepository.findByReservaId(reservaId)
                .map(voucherMapper::toDomain);
    }

    @Override
    public Optional<Voucher> findByVoucherId(Long voucherId) {
        return jpaRepository.findByVoucherId(voucherId)
                .map(voucherMapper::toDomain);
    }

    @Override
    public Optional<Voucher> findTopByNumeroVoucherContains(String numeroVoucher) {
        return jpaRepository.findTopByNumeroVoucherContains(numeroVoucher)
                .map(voucherMapper::toDomain);
    }

    @Override
    public Optional<Voucher> findTopByNumeroVoucherContainsAndFechaTomaAfter(String numeroVoucher,
            OffsetDateTime fechaToma) {
        return jpaRepository.findTopByNumeroVoucherContainsAndFechaTomaAfter(numeroVoucher, fechaToma)
                .map(voucherMapper::toDomain);
    }

    @Override
    public Voucher save(Voucher voucher) {
        return voucherMapper.toDomain(jpaRepository.save(voucherMapper.toEntity(voucher)));
    }

}
