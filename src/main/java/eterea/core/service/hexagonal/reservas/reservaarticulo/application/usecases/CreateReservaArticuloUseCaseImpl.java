package eterea.core.service.hexagonal.reservas.reservaarticulo.application.usecases;

import eterea.core.service.hexagonal.reservas.reservaarticulo.domain.model.ReservaArticulo;
import eterea.core.service.hexagonal.reservas.reservaarticulo.domain.ports.in.CreateReservaArticuloUseCase;
import eterea.core.service.hexagonal.reservas.reservaarticulo.domain.ports.out.ReservaArticuloRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateReservaArticuloUseCaseImpl implements CreateReservaArticuloUseCase {

    private final ReservaArticuloRepository repository;

    @Override
    public ReservaArticulo create(ReservaArticulo reservaArticulo) {
        return repository.save(reservaArticulo);
    }
}
