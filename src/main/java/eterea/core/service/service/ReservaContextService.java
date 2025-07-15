package eterea.core.service.service;

import eterea.core.service.kotlin.exception.ReservaContextException;
import eterea.core.service.kotlin.repository.ReservaContextRepository;
import eterea.core.service.model.ReservaContext;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ReservaContextService {

    private final ReservaContextRepository repository;

    public ReservaContextService(ReservaContextRepository repository) {
        this.repository = repository;
    }

    public List<ReservaContext> findAllByFacturacionPendiente() {
        return repository.findAllByFacturaPendiente((byte) 1);
    }

    public ReservaContext findByVoucherId(Long voucherId) {
        return Objects.requireNonNull(repository.findByVoucherId(voucherId)).orElseThrow(() -> new ReservaContextException("voucher", voucherId));
    }

    public ReservaContext findByReservaId(Long reservaId) {
        return repository.findByReservaId(reservaId).orElseThrow(() -> new ReservaContextException("reserva", reservaId));
    }

    public ReservaContext add(ReservaContext reservaContext) {
        return repository.save(reservaContext);
    }

    public ReservaContext update(ReservaContext newReservaContext, Long reservaContextId) {
        ReservaContext reservaContext = repository.findByReservaContextId(reservaContextId)
                .orElseThrow(() -> new ReservaContextException(reservaContextId));

        reservaContext.setReservaId(newReservaContext.getReservaId());
        reservaContext.setVoucherId(newReservaContext.getVoucherId());
        reservaContext.setClienteMovimientoId(newReservaContext.getClienteMovimientoId());
        reservaContext.setOrderNumberId(newReservaContext.getOrderNumberId());
        reservaContext.setFacturadoFuera(newReservaContext.getFacturadoFuera());
        reservaContext.setFacturaPendiente(newReservaContext.getFacturaPendiente());
        reservaContext.setFacturaTries(newReservaContext.getFacturaTries());
        reservaContext.setEnvioPendiente(newReservaContext.getEnvioPendiente());
        reservaContext.setEnvioTries(newReservaContext.getEnvioTries());
        reservaContext.setDiferenciaWeb(newReservaContext.getDiferenciaWeb());
        reservaContext.setFacturaArca(newReservaContext.getFacturaArca());
        reservaContext.setPayloadArca(newReservaContext.getPayloadArca());

        return repository.save(reservaContext);
    }

}
