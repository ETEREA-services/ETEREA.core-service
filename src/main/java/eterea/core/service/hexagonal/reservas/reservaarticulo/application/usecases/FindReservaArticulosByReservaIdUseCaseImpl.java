package eterea.core.service.hexagonal.reservas.reservaarticulo.application.usecases;

import eterea.core.service.hexagonal.reservas.reservaarticulo.domain.model.ReservaArticulo;
import eterea.core.service.hexagonal.reservas.reservaarticulo.domain.ports.in.FindReservaArticulosByReservaIdUseCase;
import eterea.core.service.hexagonal.reservas.reservaarticulo.domain.ports.out.ReservaArticuloRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FindReservaArticulosByReservaIdUseCaseImpl implements FindReservaArticulosByReservaIdUseCase {

    private final ReservaArticuloRepository repository;

    @Override
    public List<ReservaArticulo> findAllByReservaId(Long reservaId) {
        return repository.findAllByReservaId(reservaId);
    }
}
