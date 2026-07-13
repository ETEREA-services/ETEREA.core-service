package eterea.core.service.hexagonal.comprobante.infrastructure.web.controller;

import java.util.List;
import java.util.stream.Collectors;

import eterea.core.service.hexagonal.comprobante.application.exception.ComprobanteException;
import eterea.core.service.hexagonal.comprobante.domain.model.Comprobante;
import eterea.core.service.hexagonal.comprobante.infrastructure.web.dto.ComprobanteResponse;
import eterea.core.service.hexagonal.comprobante.infrastructure.web.mapper.ComprobanteDtoMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eterea.core.service.hexagonal.comprobante.application.service.ComprobanteService;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping({"/api/core/comprobante", "/comprobante"})
public class ComprobanteController {

    private final ComprobanteService service;
    private final ComprobanteDtoMapper mapper;

    public ComprobanteController(ComprobanteService service, ComprobanteDtoMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping("/modulo/{modulo}/{debita}/{comprobanteId}")
    public ResponseEntity<List<ComprobanteResponse>> findAllByModulo(@PathVariable Integer modulo,
                                                                     @PathVariable Byte debita,
                                                                     @PathVariable Integer comprobanteId) {
        List<ComprobanteResponse> responses = service.findAllByModulo(modulo, debita, comprobanteId).stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    @GetMapping("/disponibles")
    public ResponseEntity<List<Integer>> findAllDisponibles() {
        return new ResponseEntity<>(service.findAllDisponibles(), HttpStatus.OK);
    }

    @GetMapping("/{comprobanteId}")
    public ResponseEntity<ComprobanteResponse> findByComprobanteId(@PathVariable Integer comprobanteId) {
        try {
            Comprobante domain = service.findByComprobanteId(comprobanteId);
            return new ResponseEntity<>(mapper.toResponse(domain), HttpStatus.OK);
        } catch (ComprobanteException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

}
