package eterea.core.service.hexagonal.reservas.reservaarticulo.application.usecases;

import eterea.core.service.hexagonal.reservas.reservaarticulo.application.exception.ReservaArticuloException;
import eterea.core.service.hexagonal.reservas.reservaarticulo.domain.model.ReservaArticulo;
import eterea.core.service.hexagonal.reservas.reservaarticulo.domain.ports.in.UpdateReservaArticuloUseCase;
import eterea.core.service.hexagonal.reservas.reservaarticulo.domain.ports.out.ReservaArticuloRepository;
import org.springframework.stereotype.Component;

@Component
public class UpdateReservaArticuloUseCaseImpl implements UpdateReservaArticuloUseCase {

    private final ReservaArticuloRepository repository;

    public UpdateReservaArticuloUseCaseImpl(ReservaArticuloRepository repository) {
        this.repository = repository;
    }

    @Override
    public ReservaArticulo update(ReservaArticulo newReservaArticulo, Long reservaArticuloId) {
        ReservaArticulo reservaArticulo = repository.findByReservaArticuloId(reservaArticuloId)
                .orElseThrow(() -> new ReservaArticuloException(reservaArticuloId));

        reservaArticulo.setNegocioId(newReservaArticulo.getNegocioId());
        reservaArticulo.setReservaId(newReservaArticulo.getReservaId());
        reservaArticulo.setVoucherId(newReservaArticulo.getVoucherId());
        reservaArticulo.setArticuloId(newReservaArticulo.getArticuloId());
        reservaArticulo.setCantidad(newReservaArticulo.getCantidad());
        reservaArticulo.setComision(newReservaArticulo.getComision());
        reservaArticulo.setPrecioUnitarioSinComision(newReservaArticulo.getPrecioUnitarioSinComision());
        reservaArticulo.setPrecioUnitario(newReservaArticulo.getPrecioUnitario());
        reservaArticulo.setPrecioUnitarioSinDescuento(newReservaArticulo.getPrecioUnitarioSinDescuento());
        reservaArticulo.setPrecioCompra(newReservaArticulo.getPrecioCompra());
        reservaArticulo.setObservaciones(newReservaArticulo.getObservaciones());
        reservaArticulo.setTrackUuid(newReservaArticulo.getTrackUuid());

        return repository.save(reservaArticulo);
    }
}
