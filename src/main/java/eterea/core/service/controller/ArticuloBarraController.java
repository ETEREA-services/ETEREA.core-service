package eterea.core.service.controller;

import eterea.core.service.kotlin.exception.ArticuloBarraException;
import eterea.core.service.kotlin.model.ArticuloBarra;
import eterea.core.service.service.ArticuloBarraService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping({"/api/core/articulobarra", "/articulobarra"})
@Slf4j
public class ArticuloBarraController {

    private final ArticuloBarraService service;

    public ArticuloBarraController(ArticuloBarraService service) {
        this.service = service;
    }

    @GetMapping("/articulo/{articuloId}")
    public ResponseEntity<List<ArticuloBarra>> findAllByArticuloId(@PathVariable String articuloId) {
        return ResponseEntity.ok(service.findAllByArticuloId(articuloId));
    }

    @GetMapping("/{codigobarras}")
    public ResponseEntity<ArticuloBarra> findByCodigoBarras(@PathVariable String codigobarras) {
        try {
            return ResponseEntity.ok(service.findByCodigoBarras(codigobarras));
        } catch (ArticuloBarraException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @DeleteMapping("/{codigoBarras}")
    public ResponseEntity<Void> delete(@PathVariable String codigoBarras) {
        log.debug("Deleting barcode: {}", codigoBarras);
        try {
            service.delete(codigoBarras);
            return ResponseEntity.noContent().build();
        } catch (ArticuloBarraException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<ArticuloBarra> add(@RequestBody ArticuloBarra articuloBarra) {
        log.debug("Adding barcode: {}", articuloBarra.getCodigoBarras());
        try {
            ArticuloBarra created = service.add(articuloBarra);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch (ArticuloBarraException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }


}
