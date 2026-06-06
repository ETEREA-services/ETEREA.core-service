package eterea.core.service.controller;

import eterea.core.service.kotlin.exception.ConceptoFacturadoException;
import eterea.core.service.kotlin.model.ConceptoFacturado;
import eterea.core.service.service.ConceptoFacturadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping({"/api/core/conceptoFacturado", "/conceptoFacturado"})
@RequiredArgsConstructor
public class ConceptoFacturadoController {

    private final ConceptoFacturadoService service;

    @GetMapping("/articuloMovimiento/{articuloMovimientoId}")
    public ResponseEntity<ConceptoFacturado> findByArticuloMovimientoId(@PathVariable Long articuloMovimientoId) {
        try {
            return ResponseEntity.ok(service.findByArticuloMovimientoId(articuloMovimientoId));
        } catch (ConceptoFacturadoException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

}
