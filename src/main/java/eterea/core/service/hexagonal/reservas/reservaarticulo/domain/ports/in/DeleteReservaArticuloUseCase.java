package eterea.core.service.hexagonal.reservas.reservaarticulo.domain.ports.in;

public interface DeleteReservaArticuloUseCase {
    void deleteByReservaArticuloId(Long reservaArticuloId);
}
