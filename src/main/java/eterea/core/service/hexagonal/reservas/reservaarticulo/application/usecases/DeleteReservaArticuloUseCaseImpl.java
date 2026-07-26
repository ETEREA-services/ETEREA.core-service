package eterea.core.service.hexagonal.reservas.reservaarticulo.application.usecases;

import eterea.core.service.hexagonal.reservas.reservaarticulo.domain.ports.in.DeleteReservaArticuloUseCase;
import eterea.core.service.hexagonal.reservas.reservaarticulo.domain.ports.out.ReservaArticuloRepository;
import org.springframework.stereotype.Component;

@Component
public class DeleteReservaArticuloUseCaseImpl implements DeleteReservaArticuloUseCase {

    private final ReservaArticuloRepository repository;

    public DeleteReservaArticuloUseCaseImpl(ReservaArticuloRepository repository) {
        this.repository = repository;
    }

    @Override
    public void deleteByReservaArticuloId(Long reservaArticuloId) {
        repository.deleteByReservaArticuloId(reservaArticuloId);
    }
}
