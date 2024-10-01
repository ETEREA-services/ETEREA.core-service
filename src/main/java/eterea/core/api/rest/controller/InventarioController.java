package eterea.core.api.rest.controller;

import eterea.core.api.rest.kotlin.model.Inventario;
import eterea.core.api.rest.service.InventarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping({"/api/core/inventario", "/inventario"})
public class InventarioController {

    private final InventarioService service;

    public InventarioController(InventarioService service) {
        this.service = service;
    }

    @PostMapping("/all")
    public ResponseEntity<List<Inventario>> addAll(@RequestBody List<Inventario> inventarios) {
        return new ResponseEntity<>(service.addAll(inventarios), HttpStatus.OK);
    }

}
