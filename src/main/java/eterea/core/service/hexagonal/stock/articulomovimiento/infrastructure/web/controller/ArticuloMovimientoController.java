package eterea.core.service.hexagonal.stock.articulomovimiento.infrastructure.web.controller;

import eterea.core.service.hexagonal.stock.articulomovimiento.application.service.ArticuloMovimientoService;
import eterea.core.service.hexagonal.stock.articulomovimiento.infrastructure.web.dto.ArticuloMovimientoResponse;
import eterea.core.service.hexagonal.stock.articulomovimiento.infrastructure.web.mapper.ArticuloMovimientoDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping({"/api/core/articuloMovimiento", "/articuloMovimiento"})
@RequiredArgsConstructor
public class ArticuloMovimientoController {

    private final ArticuloMovimientoService service;
    private final ArticuloMovimientoDtoMapper mapper;

    @GetMapping("/clienteMovimiento/{clienteMovimientoId}")
    public ResponseEntity<List<ArticuloMovimientoResponse>> findAllByClienteMovimientoId(@PathVariable Long clienteMovimientoId) {
        return ResponseEntity.ok(
                service.findAllByClienteMovimientoId(clienteMovimientoId)
                        .stream().map(mapper::toResponse).collect(Collectors.toList())
        );
    }

    @GetMapping("/{articuloMovimientoId}")
    public ResponseEntity<ArticuloMovimientoResponse> findByArticuloMovimientoId(@PathVariable Long articuloMovimientoId) {
        return ResponseEntity.ok(mapper.toResponse(service.findByArticuloMovimientoId(articuloMovimientoId)));
    }

}
