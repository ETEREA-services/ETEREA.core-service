package eterea.core.service.hexagonal.reservas.reservaarticulo.application.usecases;

import eterea.core.service.hexagonal.reservas.reservaarticulo.domain.model.ReservaArticulo;
import eterea.core.service.hexagonal.reservas.reservaarticulo.domain.ports.in.SaveAllReservaArticulosUseCase;
import eterea.core.service.hexagonal.reservas.reservaarticulo.domain.ports.out.ReservaArticuloRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SaveAllReservaArticulosUseCaseImpl implements SaveAllReservaArticulosUseCase {

    private final ReservaArticuloRepository repository;

    @Override
    public List<ReservaArticulo> saveAll(List<ReservaArticulo> reservaArticulos) {
        return repository.saveAll(reservaArticulos);
    }
}
