package eterea.core.service.hexagonal.reservas.reservaarticulo.domain.ports.in;

import eterea.core.service.hexagonal.reservas.reservaarticulo.domain.model.ReservaArticulo;

public interface CreateReservaArticuloUseCase {
    ReservaArticulo create(ReservaArticulo reservaArticulo);
}
