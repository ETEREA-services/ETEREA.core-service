package eterea.core.service.controller;

import eterea.core.service.kotlin.model.Moneda;
import eterea.core.service.service.MonedaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping({"/api/core/moneda", "/moneda"})
public class MonedaController {

    private final MonedaService service;

    public MonedaController(MonedaService service) {
        this.service = service;
    }

    @GetMapping("/")
    public ResponseEntity<List<Moneda>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{monedaId}")
    public ResponseEntity<Moneda> findByMonedaId(@PathVariable Integer monedaId) {
        return ResponseEntity.ok(service.findByMonedaId(monedaId));
    }

}
