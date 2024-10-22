package eterea.core.service.service;

import eterea.core.service.kotlin.exception.ArticuloSaldoFechaException;
import eterea.core.service.kotlin.model.ArticuloSaldoFecha;
import eterea.core.service.kotlin.repository.ArticuloSaldoFechaRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Service
public class ArticuloSaldoFechaService {

    private final ArticuloSaldoFechaRepository repository;

    public ArticuloSaldoFechaService(ArticuloSaldoFechaRepository repository) {
        this.repository = repository;
    }

    public List<ArticuloSaldoFecha> findAllByArticulos(Integer centroStockId, OffsetDateTime fechaMovimiento, List<String> articuloIds) {
        return repository.findAllByCentroStockIdAndFechaAndArticuloIdIn(centroStockId, fechaMovimiento, articuloIds);
    }

    public ArticuloSaldoFecha findByUnique(Integer centroStockId, String articuloId, OffsetDateTime fechaMovimiento) {
        return repository.findByCentroStockIdAndArticuloIdAndFecha(centroStockId, articuloId, fechaMovimiento)
                .orElseThrow(() -> new ArticuloSaldoFechaException(centroStockId, articuloId, fechaMovimiento));
    }

    public ArticuloSaldoFecha save(ArticuloSaldoFecha articuloSaldoFecha) {
        return repository.save(articuloSaldoFecha);
    }

    public List<ArticuloSaldoFecha> saveAll(List<ArticuloSaldoFecha> articuloSaldoFechas) {
        return repository.saveAll(articuloSaldoFechas);
    }

    public BigDecimal calculateSaldo(Integer centroStockId, String articuloId, OffsetDateTime fecha) {
        return repository.calculateSaldo(centroStockId, articuloId, fecha);
    }

}
