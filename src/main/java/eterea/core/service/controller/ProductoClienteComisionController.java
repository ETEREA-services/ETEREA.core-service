package eterea.core.service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eterea.core.service.kotlin.model.ProductoClienteComision;
import eterea.core.service.service.ProductoClienteComisionService;

@RestController
@RequestMapping({ "/api/core/productoclientecomision", "/productoclientecomision" })
public class ProductoClienteComisionController {

    private final ProductoClienteComisionService service;

    public ProductoClienteComisionController(ProductoClienteComisionService service) {
        this.service = service;
    }

    @GetMapping("/producto/{productoId}/cliente/{clienteId}")
    public ResponseEntity<ProductoClienteComision> findByProductoIdAndClienteId(@PathVariable Long productoId,
            @PathVariable Long clienteId) {
        return ResponseEntity.ok(service.findByProductoIdAndClienteId(productoId, clienteId));
    }

}
