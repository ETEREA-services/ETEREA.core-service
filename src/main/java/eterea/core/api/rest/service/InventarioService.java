package eterea.core.api.rest.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import eterea.core.api.rest.kotlin.exception.InventarioException;
import eterea.core.api.rest.kotlin.model.Inventario;
import eterea.core.api.rest.kotlin.repository.InventarioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
@Slf4j
public class InventarioService {

    private final InventarioRepository repository;

    private final ArticuloSaldoFechaService articuloSaldoFechaService;

    public InventarioService(InventarioRepository repository, ArticuloSaldoFechaService articuloSaldoFechaService) {
        this.repository = repository;
        this.articuloSaldoFechaService = articuloSaldoFechaService;
    }

    public List<Inventario> addAll(List<Inventario> inventarios) {
        for (var inventario : inventarios) {
            try {
                log.debug("Inventario -> {}", JsonMapper.builder().findAndAddModules().build().writerWithDefaultPrettyPrinter().writeValueAsString(inventario));
            } catch (JsonProcessingException e) {
                log.debug("Inventario error -> {}", e.getMessage());
            }
            try {
                var inventarioPrevio = findByUnique(inventario.getFecha(), inventario.getInventarioTurnoId(), inventario.getCentroStockId(), inventario.getArticuloId());
                inventario.setInventarioId(inventarioPrevio.getInventarioId());
                try {
                    log.debug("Inventario nuevo -> {}", JsonMapper.builder().findAndAddModules().build().writerWithDefaultPrettyPrinter().writeValueAsString(inventario));
                } catch (JsonProcessingException e) {
                    log.debug("Inventario nuevo error -> {}", e.getMessage());
                }
            } catch (InventarioException e) {
                log.debug("Inventario nuevo -> {}", e.getMessage());
            }
            var stock = articuloSaldoFechaService.calculateSaldo(inventario.getCentroStockId(), inventario.getArticuloId(), inventario.getFecha());
            log.debug("Calculo Saldo -> {}", stock);
            inventario.setStock(stock);
        }
        return repository.saveAll(inventarios);
    }

    private Inventario findByUnique(OffsetDateTime fecha, Integer inventarioTurnoId, Integer centroStockId, String articuloId) {
        return repository.findByFechaAndInventarioTurnoIdAndCentroStockIdAndArticuloId(fecha, inventarioTurnoId, centroStockId, articuloId).orElseThrow(() -> new InventarioException(fecha, inventarioTurnoId, centroStockId, articuloId));
    }

}
