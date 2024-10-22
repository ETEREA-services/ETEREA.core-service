package eterea.core.service.service.view;

import eterea.core.service.kotlin.exception.view.SaldoFechaException;
import eterea.core.service.kotlin.model.view.SaldoFecha;
import eterea.core.service.kotlin.repository.view.SaldoFechaRepository;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
public class SaldoFechaService {

    private final SaldoFechaRepository repository;

    public SaldoFechaService(SaldoFechaRepository repository) {
        this.repository = repository;
    }

    public List<SaldoFecha> findAllByArticulos(Integer centroStockId, OffsetDateTime fechaMovimiento, List<String> articuloIds) {
        return repository.findAllByCentroStockIdAndFechaAndArticuloIdIn(centroStockId, fechaMovimiento, articuloIds);
    }

    public SaldoFecha findByUnique(Integer centroStockId, String articuloId, OffsetDateTime fechaMovimiento) {
        return repository.findByCentroStockIdAndArticuloIdAndFecha(centroStockId, articuloId, fechaMovimiento)
                .orElseThrow(() -> new SaldoFechaException(centroStockId, articuloId, fechaMovimiento));
    }

}
