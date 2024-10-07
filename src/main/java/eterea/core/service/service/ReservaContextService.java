package eterea.core.service.service;

import eterea.core.service.kotlin.exception.ReservaContextException;
import eterea.core.service.kotlin.model.ReservaContext;
import eterea.core.service.kotlin.repository.ReservaContextRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return repository.findByVoucherId(voucherId).orElseThrow(() -> new ReservaContextException("voucher", voucherId));
    }

    public ReservaContext findByReservaId(Long reservaId) {
        return repository.findByReservaId(reservaId).orElseThrow(() -> new ReservaContextException("reserva", reservaId));
    }

    public ReservaContext add(ReservaContext reservaContext) {
        return repository.save(reservaContext);
    }

    public ReservaContext update(ReservaContext newReservaContext, Long reservaContextId) {
        return repository.findByReservaContextId(reservaContextId).map(reservaContext -> {
            reservaContext = new ReservaContext.Builder()
                    .reservaContextId(reservaContextId)
                    .reservaId(newReservaContext.getReservaId())
                    .voucherId(newReservaContext.getVoucherId())
                    .clienteMovimientoId(newReservaContext.getClienteMovimientoId())
                    .orderNumberId(newReservaContext.getOrderNumberId())
                    .facturadoFuera(newReservaContext.getFacturadoFuera())
                    .facturaPendiente(newReservaContext.getFacturaPendiente())
                    .facturaTries(newReservaContext.getFacturaTries())
                    .envioPendiente(newReservaContext.getEnvioPendiente())
                    .envioTries(newReservaContext.getEnvioTries())
                    .build();
            return repository.save(reservaContext);
        }).orElseThrow(() -> new ReservaContextException(reservaContextId));
    }

}
