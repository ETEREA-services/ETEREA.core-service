/**
 *
 */
package eterea.core.service.controller;

import java.util.List;

import eterea.core.service.kotlin.model.Articulo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eterea.core.service.service.ArticuloService;

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
        log.debug("Trying Articulo Nativo -> {}", articuloId);
        return new ResponseEntity<>(service.findByArticuloId(articuloId), HttpStatus.OK);
    }

    @GetMapping("/autonumerico/{autonumerico}")
    public ResponseEntity<Articulo> findByAutonumerico(@PathVariable Long autonumerico) {
        return new ResponseEntity<>(service.findByAutoNumericoId(autonumerico), HttpStatus.OK);
    }

    @GetMapping("/mascaraBalanza/{mascaraBalanza}")
    public ResponseEntity<Articulo> findByMascaraBalanza(@PathVariable String mascaraBalanza) {
        return new ResponseEntity<>(service.findByMascaraBalanza(mascaraBalanza), HttpStatus.OK);
    }

}
