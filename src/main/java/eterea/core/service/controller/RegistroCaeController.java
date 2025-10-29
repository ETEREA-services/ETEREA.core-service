package eterea.core.service.controller;

import eterea.core.service.kotlin.exception.RegistroCaeException;
import eterea.core.service.model.RegistroCae;
import eterea.core.service.service.RegistroCaeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping({"/api/core/registroCae", "/registroCae"})
@RequiredArgsConstructor
public class RegistroCaeController {

    private final RegistroCaeService service;

    @GetMapping("/unique/{comprobanteId}/{puntoVenta}/{numeroComprobante}")
    public ResponseEntity<RegistroCae> findByUnique(@PathVariable Integer comprobanteId, @PathVariable Integer puntoVenta, @PathVariable Long numeroComprobante) {
        try {
            return new ResponseEntity<>(service.findByUnique(comprobanteId, puntoVenta, numeroComprobante), HttpStatus.OK);
        } catch (RegistroCaeException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping("/")
    public ResponseEntity<RegistroCae> create(@RequestBody RegistroCae registroCae) {
        return new ResponseEntity<>(service.add(registroCae), HttpStatus.CREATED);
    }

}
