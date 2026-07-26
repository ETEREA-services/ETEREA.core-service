package eterea.core.service.hexagonal.reservas.reservaarticulo.domain.ports.in;

import eterea.core.service.hexagonal.reservas.reservaarticulo.domain.model.ReservaArticulo;

import java.util.List;

public interface FindReservaArticulosByVoucherIdUseCase {
    List<ReservaArticulo> findAllByReservaIdAndVoucherId(Long reservaId, Long voucherId);
}
