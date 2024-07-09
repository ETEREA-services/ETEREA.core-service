package eterea.core.api.rest.controller;

import eterea.core.api.rest.kotlin.model.Moneda;
import eterea.core.api.rest.service.MonedaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/core/moneda")
public class MonedaController {

    private final MonedaService service;

    public MonedaController(MonedaService service) {
        this.service = service;
    }

    @GetMapping("/{monedaId}")
    public ResponseEntity<Moneda> findByMonedaId(@PathVariable Integer monedaId) {
        return ResponseEntity.ok(service.findByMonedaId(monedaId));
    }

}
