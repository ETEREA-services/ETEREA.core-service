package eterea.core.api.rest.controller;

import eterea.core.api.rest.kotlin.exception.RegistroCaeException;
import eterea.core.api.rest.kotlin.model.RegistroCae;
import eterea.core.api.rest.service.RegistroCaeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping({"/api/core/registroCae", "/registroCae"})
public class RegistroCaeController {

    private final RegistroCaeService service;

    @Autowired
    public RegistroCaeController(RegistroCaeService service) {
        this.service = service;
    }

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
