package eterea.core.service.hexagonal.reservas.reservaarticulo.application.service;

import eterea.core.service.hexagonal.reservas.reservaarticulo.domain.model.ReservaArticulo;
import eterea.core.service.hexagonal.reservas.reservaarticulo.domain.ports.in.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ReservaArticuloService {

    private final FindReservaArticulosByReservaIdUseCase findReservaArticulosByReservaIdUseCase;
    private final FindReservaArticulosByVoucherIdUseCase findReservaArticulosByVoucherIdUseCase;
    private final FindReservaArticuloByIdUseCase findReservaArticuloByIdUseCase;
    private final CreateReservaArticuloUseCase createReservaArticuloUseCase;
    private final UpdateReservaArticuloUseCase updateReservaArticuloUseCase;
    private final DeleteReservaArticuloUseCase deleteReservaArticuloUseCase;
    private final SaveAllReservaArticulosUseCase saveAllReservaArticulosUseCase;

    public ReservaArticuloService(FindReservaArticulosByReservaIdUseCase findReservaArticulosByReservaIdUseCase,
                                  FindReservaArticulosByVoucherIdUseCase findReservaArticulosByVoucherIdUseCase,
                                  FindReservaArticuloByIdUseCase findReservaArticuloByIdUseCase,
                                  CreateReservaArticuloUseCase createReservaArticuloUseCase,
                                  UpdateReservaArticuloUseCase updateReservaArticuloUseCase,
                                  DeleteReservaArticuloUseCase deleteReservaArticuloUseCase,
                                  SaveAllReservaArticulosUseCase saveAllReservaArticulosUseCase) {
        this.findReservaArticulosByReservaIdUseCase = findReservaArticulosByReservaIdUseCase;
        this.findReservaArticulosByVoucherIdUseCase = findReservaArticulosByVoucherIdUseCase;
        this.findReservaArticuloByIdUseCase = findReservaArticuloByIdUseCase;
        this.createReservaArticuloUseCase = createReservaArticuloUseCase;
        this.updateReservaArticuloUseCase = updateReservaArticuloUseCase;
        this.deleteReservaArticuloUseCase = deleteReservaArticuloUseCase;
        this.saveAllReservaArticulosUseCase = saveAllReservaArticulosUseCase;
    }

    public List<ReservaArticulo> findAllByReservaId(Long reservaId) {
        return findReservaArticulosByReservaIdUseCase.findAllByReservaId(reservaId);
    }

    public List<ReservaArticulo> findAllByVoucherId(Long reservaId, Long voucherId) {
        return findReservaArticulosByVoucherIdUseCase.findAllByReservaIdAndVoucherId(reservaId, voucherId);
    }

    public ReservaArticulo findByReservaArticuloId(Long reservaArticuloId) {
        return findReservaArticuloByIdUseCase.findByReservaArticuloId(reservaArticuloId);
    }

    public ReservaArticulo add(ReservaArticulo reservaArticulo) {
        return createReservaArticuloUseCase.create(reservaArticulo);
    }

    public ReservaArticulo update(ReservaArticulo newReservaArticulo, Long reservaArticuloId) {
        return updateReservaArticuloUseCase.update(newReservaArticulo, reservaArticuloId);
    }

    public void deleteByReservaArticuloId(Long reservaArticuloId) {
        deleteReservaArticuloUseCase.deleteByReservaArticuloId(reservaArticuloId);
    }

    public List<ReservaArticulo> saveAll(List<ReservaArticulo> reservaArticulos) {
        return saveAllReservaArticulosUseCase.saveAll(reservaArticulos);
    }

}
