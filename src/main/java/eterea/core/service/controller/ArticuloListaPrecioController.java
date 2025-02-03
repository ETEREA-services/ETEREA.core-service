package eterea.core.service.controller;

import eterea.core.service.kotlin.model.ArticuloListaPrecio;
import eterea.core.service.service.ArticuloListaPrecioService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping({"/api/core/articuloListaPrecio", "/articuloListaPrecio"})
public class ArticuloListaPrecioController {

    private final ArticuloListaPrecioService service;

    public ArticuloListaPrecioController(ArticuloListaPrecioService service) {
        this.service = service;
    }

    @GetMapping("/page")
    public ResponseEntity<Page<ArticuloListaPrecio>> findAllPublicadosPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "30") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(service.findAllPublicadosPaginated(pageable));
    }

    @GetMapping("/{articuloId}")
    public ResponseEntity<ArticuloListaPrecio> findByArticuloId(@PathVariable String articuloId) {
        return ResponseEntity.ok(service.findByArticuloId(articuloId));
    }

    @GetMapping("/publicar/{articuloId}/{publicar}")
    public ResponseEntity<ArticuloListaPrecio> publicar(@PathVariable String articuloId, @PathVariable Byte publicar) {
        return ResponseEntity.ok(service.publicar(articuloId, publicar));
    }

}
