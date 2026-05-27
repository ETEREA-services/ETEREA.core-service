package eterea.core.service.hexagonal.articulo.infrastructure.web.controller;

import eterea.core.service.hexagonal.articulo.application.exception.ArticuloException;
import eterea.core.service.hexagonal.articulo.application.service.ArticuloService;
import eterea.core.service.hexagonal.articulo.domain.model.Articulo;
import eterea.core.service.hexagonal.articulo.infrastructure.web.dto.ArticuloRequest;
import eterea.core.service.hexagonal.articulo.infrastructure.web.dto.ArticuloResponse;
import eterea.core.service.hexagonal.articulo.infrastructure.web.dto.TotalArticuloResponse;
import eterea.core.service.hexagonal.articulo.infrastructure.web.mapper.ArticuloDtoMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping({"/api/core/articulo", "/articulo"})
@Slf4j
@RequiredArgsConstructor
public class ArticuloController {

    private final ArticuloService service;
    private final ArticuloDtoMapper mapper;

    @GetMapping("/")
    public ResponseEntity<List<ArticuloResponse>> findAll() {
        return ResponseEntity.ok(service.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList()));
    }

    @GetMapping("/search/{chain}")
    public ResponseEntity<List<ArticuloResponse>> findAllBySearch(@PathVariable String chain) {
        return ResponseEntity.ok(service.findAllBySearch(chain).stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList()));
    }

    @GetMapping("/{articuloId}")
    public ResponseEntity<ArticuloResponse> findByArticuloId(@PathVariable String articuloId) {
        log.debug("processing findByArticuloId -> {}", articuloId);
        try {
            return ResponseEntity.ok(mapper.toResponse(service.findByArticuloId(articuloId)));
        } catch (ArticuloException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/autonumerico/{autonumerico}")
    public ResponseEntity<ArticuloResponse> findByAutonumerico(@PathVariable Long autonumerico) {
        try {
            return ResponseEntity.ok(mapper.toResponse(service.findByAutoNumerico(autonumerico)));
        } catch (ArticuloException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/mascaraBalanza/{mascaraBalanza}")
    public ResponseEntity<ArticuloResponse> findByMascaraBalanza(@PathVariable String mascaraBalanza) {
        try {
            return ResponseEntity.ok(mapper.toResponse(service.findByMascaraBalanza(mascaraBalanza)));
        } catch (ArticuloException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping(value = "/", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ArticuloResponse> add(@RequestBody ArticuloRequest request) {
        Articulo articulo = mapper.toDomain(request);
        return ResponseEntity.ok(mapper.toResponse(service.add(articulo)));
    }

    @PutMapping("/{articuloId}")
    public ResponseEntity<ArticuloResponse> update(@RequestBody ArticuloRequest request, @PathVariable String articuloId) {
        Articulo articulo = mapper.toDomain(request);
        return ResponseEntity.ok(mapper.toResponse(service.update(articulo, articuloId)));
    }

    @GetMapping("/calculo/totales/{articuloId}/cantidad/{cantidad}/unitario/{precioUnitarioConIva}")
    public ResponseEntity<TotalArticuloResponse> calculateTotales(@PathVariable String articuloId, @PathVariable BigDecimal cantidad, @PathVariable BigDecimal precioUnitarioConIva) {
        return ResponseEntity.ok(service.calculateTotales(articuloId, cantidad, precioUnitarioConIva));
    }

}
