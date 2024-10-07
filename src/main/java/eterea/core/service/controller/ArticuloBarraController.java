package eterea.core.service.controller;

import eterea.core.service.kotlin.model.ArticuloBarra;
import eterea.core.service.service.ArticuloBarraService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/api/core/articulobarra", "/articulobarra"})
public class ArticuloBarraController {

    private final ArticuloBarraService service;

    public ArticuloBarraController(ArticuloBarraService service) {
        this.service = service;
    }

    @GetMapping("/{codigobarras}")
    public ResponseEntity<ArticuloBarra> findByCodigoBarras(@PathVariable String codigobarras) {
        return ResponseEntity.ok(service.findByCodigoBarras(codigobarras));
    }

}
