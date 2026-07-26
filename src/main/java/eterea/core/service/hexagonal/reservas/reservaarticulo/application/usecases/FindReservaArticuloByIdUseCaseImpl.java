package eterea.core.service.hexagonal.reservas.reservaarticulo.application.usecases;

import eterea.core.service.hexagonal.reservas.reservaarticulo.application.exception.ReservaArticuloException;
import eterea.core.service.hexagonal.reservas.reservaarticulo.domain.model.ReservaArticulo;
import eterea.core.service.hexagonal.reservas.reservaarticulo.domain.ports.in.FindReservaArticuloByIdUseCase;
import eterea.core.service.hexagonal.reservas.reservaarticulo.domain.ports.out.ReservaArticuloRepository;
import org.springframework.stereotype.Component;

@Component
public class FindReservaArticuloByIdUseCaseImpl implements FindReservaArticuloByIdUseCase {

    private final ReservaArticuloRepository repository;

    public FindReservaArticuloByIdUseCaseImpl(ReservaArticuloRepository repository) {
        this.repository = repository;
    }

    @Override
    public ReservaArticulo findByReservaArticuloId(Long reservaArticuloId) {
        return repository.findByReservaArticuloId(reservaArticuloId)
                .orElseThrow(() -> new ReservaArticuloException(reservaArticuloId));
    }
}
