package eterea.core.service.service.view;

import eterea.core.service.kotlin.exception.view.SaldoArticuloException;
import eterea.core.service.kotlin.model.view.SaldoArticulo;
import eterea.core.service.kotlin.repository.view.SaldoArticuloRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaldoArticuloService {

    private final SaldoArticuloRepository repository;

    public SaldoArticuloService(SaldoArticuloRepository repository) {
        this.repository = repository;
    }

    public List<SaldoArticulo> findAllByArticulos(Integer centroStockId, List<String> articuloIds) {
        return repository.findAllByCentroStockIdAndArticuloIdIn(centroStockId, articuloIds);
    }

    public SaldoArticulo findByUnique(Integer centroStockId, String articuloId) {
        return repository.findByCentroStockIdAndArticuloId(centroStockId, articuloId)
                .orElseThrow(() -> new SaldoArticuloException(centroStockId, articuloId));
    }

}
