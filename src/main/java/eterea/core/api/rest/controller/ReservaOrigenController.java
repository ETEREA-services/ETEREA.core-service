package eterea.core.api.rest.controller;

import eterea.core.api.rest.kotlin.model.ReservaOrigen;
import eterea.core.api.rest.service.ReservaOrigenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/core/reservaorigen")
public class ReservaOrigenController {

    private final ReservaOrigenService service;

    public ReservaOrigenController(ReservaOrigenService service) {
        this.service = service;
    }

    @GetMapping("/")
    public ResponseEntity<List<ReservaOrigen>> findAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{reservaOrigenId}")
    public ResponseEntity<ReservaOrigen> findByReservaOrigenId(@PathVariable Integer reservaOrigenId) {
        return new ResponseEntity<>(service.findByReservaOrigenId(reservaOrigenId), HttpStatus.OK);
    }

}
