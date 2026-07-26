package eterea.core.service.hexagonal.reservas.reservaarticulo.domain.ports.in;

import eterea.core.service.hexagonal.reservas.reservaarticulo.domain.model.ReservaArticulo;

public interface FindReservaArticuloByIdUseCase {
    ReservaArticulo findByReservaArticuloId(Long reservaArticuloId);
}
