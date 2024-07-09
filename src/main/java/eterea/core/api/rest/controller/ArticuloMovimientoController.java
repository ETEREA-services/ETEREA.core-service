package eterea.core.api.rest.controller;

import eterea.core.api.rest.kotlin.model.ArticuloMovimiento;
import eterea.core.api.rest.service.ArticuloMovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/core/articuloMovimiento")
public class ArticuloMovimientoController {

    private final ArticuloMovimientoService service;

    @Autowired
    public ArticuloMovimientoController(ArticuloMovimientoService service) {
        this.service = service;
    }

    @GetMapping("/clienteMovimiento/{clienteMovimientoId}")
    public ResponseEntity<List<ArticuloMovimiento>> findAllByClienteMovimientoId(@PathVariable Long clienteMovimientoId) {
        return ResponseEntity.ok(service.findAllByClienteMovimientoId(clienteMovimientoId));
    }

    @GetMapping("/{articuloMovimientoId}")
    public ResponseEntity<ArticuloMovimiento> findByArticuloMovimientoId(@PathVariable Long articuloMovimientoId) {
        return new ResponseEntity<>(service.findByArticuloMovimientoId(articuloMovimientoId), HttpStatus.OK);
    }

}
