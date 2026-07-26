package eterea.core.service.hexagonal.reservas.reservaarticulo.domain.ports.in;

import eterea.core.service.hexagonal.reservas.reservaarticulo.domain.model.ReservaArticulo;

public interface UpdateReservaArticuloUseCase {
    ReservaArticulo update(ReservaArticulo newReservaArticulo, Long reservaArticuloId);
}
