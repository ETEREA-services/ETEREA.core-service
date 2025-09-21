package eterea.core.service.controller;

import eterea.core.service.kotlin.exception.ArticuloListaPrecioException;
import eterea.core.service.kotlin.model.ArticuloListaPrecio;
import eterea.core.service.service.ArticuloListaPrecioService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping({"/api/core/articuloListaPrecio", "/articuloListaPrecio"})
@RequiredArgsConstructor
public class ArticuloListaPrecioController {

    private final ArticuloListaPrecioService service;

    @GetMapping("/page")
    public ResponseEntity<Page<ArticuloListaPrecio>> findAllPublicadosPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "30") int size) {
            
        Pageable pageable = PageRequest.of(page, size);
        Page<ArticuloListaPrecio> pageResult = service.findAllPublicadosPaginated(pageable);
        
        return ResponseEntity.ok(pageResult);
    }

    @GetMapping("/{articuloId}")
    public ResponseEntity<ArticuloListaPrecio> findByArticuloId(@PathVariable String articuloId) {
        try {
            return ResponseEntity.ok(service.findByArticuloId(articuloId));
        } catch (ArticuloListaPrecioException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/publicar/{articuloId}/{publicar}")
    public ResponseEntity<ArticuloListaPrecio> publicar(@PathVariable String articuloId, @PathVariable Byte publicar) {
        return ResponseEntity.ok(service.publicar(articuloId, publicar));
    }

    @GetMapping("/rubro/{rubroId}/page")
    public ResponseEntity<Page<ArticuloListaPrecio>> findAllByRubroIdPaginated(
            @PathVariable Integer rubroId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "30") int size) {
            
        Pageable pageable = PageRequest.of(page, size);
        Page<ArticuloListaPrecio> pageResult = service.findAllByRubroIdPaginated(rubroId, pageable);
        
        return ResponseEntity.ok(pageResult);
    }

    @PostMapping("/")
    public ResponseEntity<ArticuloListaPrecio> addOrUpdate(@RequestBody ArticuloListaPrecio articuloListaPrecio) {
        return ResponseEntity.ok(service.addOrUpdate(articuloListaPrecio));
    }

}
