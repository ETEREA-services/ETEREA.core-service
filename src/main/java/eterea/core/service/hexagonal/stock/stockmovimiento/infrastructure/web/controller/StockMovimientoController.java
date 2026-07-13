package eterea.core.service.hexagonal.stock.stockmovimiento.infrastructure.web.controller;

import eterea.core.service.hexagonal.stock.stockmovimiento.application.exception.StockMovimientoException;
import eterea.core.service.hexagonal.stock.stockmovimiento.application.service.StockMovimientoService;
import eterea.core.service.hexagonal.stock.stockmovimiento.domain.model.StockMovimiento;
import eterea.core.service.hexagonal.stock.stockmovimiento.infrastructure.web.dto.StockMovimientoResponse;
import eterea.core.service.hexagonal.stock.stockmovimiento.infrastructure.web.mapper.StockMovimientoDtoMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/tenant/core/stockmovimiento")
public class StockMovimientoController {

    private final StockMovimientoService service;
    private final StockMovimientoDtoMapper mapper;

    public StockMovimientoController(StockMovimientoService service, StockMovimientoDtoMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping("/{stockMovimientoId}")
    public ResponseEntity<StockMovimientoResponse> findByStockMovimientoId(@PathVariable Long stockMovimientoId) {
        try {
            StockMovimiento domain = service.findByStockMovimientoId(stockMovimientoId);
            return new ResponseEntity<>(mapper.toResponse(domain), HttpStatus.OK);
        } catch (StockMovimientoException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/last/{comprobanteId}")
    public ResponseEntity<StockMovimientoResponse> getLastByComprobanteId(@PathVariable Integer comprobanteId) {
        try {
            StockMovimiento domain = service.getLastByComprobanteId(comprobanteId);
            return new ResponseEntity<>(mapper.toResponse(domain), HttpStatus.OK);
        } catch (StockMovimientoException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

}
