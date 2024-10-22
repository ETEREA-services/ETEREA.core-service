package eterea.core.service.controller.facade;

import eterea.core.service.kotlin.model.StockMovimiento;
import eterea.core.service.kotlin.model.dto.StockAndArticulosDto;
import eterea.core.service.service.facade.StockService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/core/stock")
public class StockController {

    private final StockService service;

    public StockController(StockService service) {
        this.service = service;
    }

    @PostMapping("/add/movimiento")
    public ResponseEntity<StockMovimiento> addMovimiento(@RequestBody StockAndArticulosDto stockAndArticulos) {
        return ResponseEntity.ok(service.addMovimiento(stockAndArticulos));
    }

}
