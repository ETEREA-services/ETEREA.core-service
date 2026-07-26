package eterea.core.service.hexagonal.cierrecaja.cierrecajamovimiento.infrastructure.web.controller;

import eterea.core.service.hexagonal.cierrecaja.cierrecajamovimiento.application.exception.CierreCajaException;
import eterea.core.service.hexagonal.cierrecaja.cierrecajamovimiento.application.service.CierreCajaService;
import eterea.core.service.hexagonal.cierrecaja.cierrecajamovimiento.domain.model.CierreCaja;
import eterea.core.service.hexagonal.cierrecaja.cierrecajamovimiento.infrastructure.web.dto.CierreCajaResponse;
import eterea.core.service.hexagonal.cierrecaja.cierrecajamovimiento.infrastructure.web.mapper.CierreCajaDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/core/cierrecajamovimiento")
@RequiredArgsConstructor
public class CierreCajaController {

    private final CierreCajaService service;
    private final CierreCajaDtoMapper mapper;

    @GetMapping("/{cierreCajaId}")
    public ResponseEntity<CierreCajaResponse> findByCierreCajaId(@PathVariable Long cierreCajaId) {
        try {
            CierreCaja domain = service.findByCierreCajaId(cierreCajaId);
            return ResponseEntity.ok(mapper.toResponse(domain));
        } catch (CierreCajaException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/last")
    public ResponseEntity<CierreCajaResponse> findLast() {
        try {
            CierreCaja domain = service.findLast();
            return ResponseEntity.ok(mapper.toResponse(domain));
        } catch (CierreCajaException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

}
