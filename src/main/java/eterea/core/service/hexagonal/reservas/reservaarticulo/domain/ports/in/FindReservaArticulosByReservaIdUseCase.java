package eterea.core.service.hexagonal.reservas.reservaarticulo.domain.ports.in;

import eterea.core.service.hexagonal.reservas.reservaarticulo.domain.model.ReservaArticulo;

import java.util.List;

public interface FindReservaArticulosByReservaIdUseCase {
    List<ReservaArticulo> findAllByReservaId(Long reservaId);
}
