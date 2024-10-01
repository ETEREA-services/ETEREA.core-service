package eterea.core.api.rest.service;

import eterea.core.api.rest.kotlin.repository.ArticuloSaldoFechaRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Service
public class ArticuloSaldoFechaService {

    private final ArticuloSaldoFechaRepository repository;

    public ArticuloSaldoFechaService(ArticuloSaldoFechaRepository repository) {
        this.repository = repository;
    }

    public BigDecimal calculateSaldo(Integer centroStockId, String articuloId, OffsetDateTime fecha) {
        return repository.calculateSaldo(centroStockId, articuloId, fecha);
    }

}
