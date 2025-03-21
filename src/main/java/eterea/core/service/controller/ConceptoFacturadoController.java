package eterea.core.service.controller;

import eterea.core.service.kotlin.exception.ConceptoFacturadoException;
import eterea.core.service.kotlin.model.ConceptoFacturado;
import eterea.core.service.service.ConceptoFacturadoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping({"/api/core/conceptoFacturado", "/conceptoFacturado"})
public class ConceptoFacturadoController {

    private final ConceptoFacturadoService service;

    public ConceptoFacturadoController(ConceptoFacturadoService service) {
        this.service = service;
    }

    @GetMapping("/articuloMovimiento/{articuloMovimientoId}")
    public ResponseEntity<ConceptoFacturado> findByArticuloMovimientoId(@PathVariable Long articuloMovimientoId) {
        try {
            return new ResponseEntity<>(service.findByArticuloMovimientoId(articuloMovimientoId), HttpStatus.OK);
        } catch (ConceptoFacturadoException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

}
