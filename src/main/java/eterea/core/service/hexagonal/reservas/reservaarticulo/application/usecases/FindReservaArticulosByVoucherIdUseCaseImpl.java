package eterea.core.service.hexagonal.reservas.reservaarticulo.application.usecases;

import eterea.core.service.hexagonal.reservas.reservaarticulo.domain.model.ReservaArticulo;
import eterea.core.service.hexagonal.reservas.reservaarticulo.domain.ports.in.FindReservaArticulosByVoucherIdUseCase;
import eterea.core.service.hexagonal.reservas.reservaarticulo.domain.ports.out.ReservaArticuloRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FindReservaArticulosByVoucherIdUseCaseImpl implements FindReservaArticulosByVoucherIdUseCase {

    private final ReservaArticuloRepository repository;

    public FindReservaArticulosByVoucherIdUseCaseImpl(ReservaArticuloRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ReservaArticulo> findAllByReservaIdAndVoucherId(Long reservaId, Long voucherId) {
        return repository.findAllByReservaIdAndVoucherId(reservaId, voucherId);
    }
}
