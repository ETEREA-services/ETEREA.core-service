package eterea.core.service.hexagonal.reservas.reservaarticulo.domain.ports.out;

import eterea.core.service.hexagonal.reservas.reservaarticulo.domain.model.ReservaArticulo;

import java.util.List;
import java.util.Optional;

public interface ReservaArticuloRepository {

    List<ReservaArticulo> findAllByReservaId(Long reservaId);

    List<ReservaArticulo> findAllByReservaIdAndVoucherId(Long reservaId, Long voucherId);

    Optional<ReservaArticulo> findByReservaArticuloId(Long reservaArticuloId);

    void deleteByReservaArticuloId(Long reservaArticuloId);

    List<ReservaArticulo> saveAll(List<ReservaArticulo> reservaArticulos);

    ReservaArticulo save(ReservaArticulo reservaArticulo);

}
