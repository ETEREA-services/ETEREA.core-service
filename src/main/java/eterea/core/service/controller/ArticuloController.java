/**
 *
 */
package eterea.core.service.controller;

import java.util.List;

import eterea.core.service.exception.ArticuloException;
import eterea.core.service.kotlin.model.Articulo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import eterea.core.service.service.ArticuloService;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping({"/api/core/articulo", "/articulo"})
@Slf4j
public class ArticuloController {

    private final ArticuloService service;

    public ArticuloController(ArticuloService service) {
        this.service = service;
    }

    @GetMapping("/")
    public ResponseEntity<List<Articulo>> findAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/search/{chain}")
    public ResponseEntity<List<Articulo>> findAllBySearch(@PathVariable String chain) {
        return new ResponseEntity<>(service.findAllBySearch(chain), HttpStatus.OK);
    }

    @GetMapping("/{articuloId}")
    public ResponseEntity<Articulo> findByArticuloId(@PathVariable String articuloId) {
        log.debug("processing findByArticuloId -> {}", articuloId);
        try {
            return new ResponseEntity<>(service.findByArticuloId(articuloId), HttpStatus.OK);
        } catch (ArticuloException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/autonumerico/{autonumerico}")
    public ResponseEntity<Articulo> findByAutonumerico(@PathVariable Long autonumerico) {
        try {
            return new ResponseEntity<>(service.findByAutoNumerico(autonumerico), HttpStatus.OK);
        } catch (ArticuloException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/mascaraBalanza/{mascaraBalanza}")
    public ResponseEntity<Articulo> findByMascaraBalanza(@PathVariable String mascaraBalanza) {
        try {
            return new ResponseEntity<>(service.findByMascaraBalanza(mascaraBalanza), HttpStatus.OK);
        } catch (ArticuloException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping("/")
    public ResponseEntity<Articulo> add(@RequestBody Articulo articulo) {
        return new ResponseEntity<>(service.add(articulo), HttpStatus.OK);
    }

}
