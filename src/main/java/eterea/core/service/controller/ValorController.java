package eterea.core.service.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eterea.core.service.kotlin.model.Valor;
import eterea.core.service.service.ValorService;

@RestController
@RequestMapping({"/api/core/valor", "/valor"})
public class ValorController {

    private final ValorService service;

    public ValorController(ValorService service) {
        this.service = service;
    }

    @GetMapping("/")
    public ResponseEntity<List<Valor>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

}
