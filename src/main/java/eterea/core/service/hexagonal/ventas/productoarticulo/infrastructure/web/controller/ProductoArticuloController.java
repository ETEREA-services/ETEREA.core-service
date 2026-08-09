package eterea.core.service.hexagonal.ventas.productoarticulo.infrastructure.web.controller;

import eterea.core.service.hexagonal.ventas.productoarticulo.application.service.ProductoArticuloService;
import eterea.core.service.hexagonal.ventas.productoarticulo.domain.model.ProductoArticulo;
import eterea.core.service.hexagonal.ventas.productoarticulo.infrastructure.web.dto.ProductoArticuloRequest;
import eterea.core.service.hexagonal.ventas.productoarticulo.infrastructure.web.dto.ProductoArticuloResponse;
import eterea.core.service.hexagonal.ventas.productoarticulo.infrastructure.web.mapper.ProductoArticuloDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping({"/api/core/productoarticulo", "/productoarticulo"})
@RequiredArgsConstructor
public class ProductoArticuloController {

    private final ProductoArticuloService service;
    private final ProductoArticuloDtoMapper mapper;

    @GetMapping("/producto/{productoId}")
    public ResponseEntity<List<ProductoArticuloResponse>> findAllByProductoId(@PathVariable Integer productoId) {
        List<ProductoArticuloResponse> responses = service.findAllByProductoId(productoId).stream()
                .map(mapper::toResponse).collect(Collectors.toList());
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<ProductoArticuloResponse> add(@RequestBody ProductoArticuloRequest productoarticulo) {
        ProductoArticulo created = service.add(mapper.toDomain(productoarticulo));
        return new ResponseEntity<>(mapper.toResponse(created), HttpStatus.OK);
    }

    @DeleteMapping("/{productoarticuloId}")
    public ResponseEntity<Void> delete(@PathVariable Long productoarticuloId) {
        service.delete(productoarticuloId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
