package eterea.core.service.controller;

import eterea.core.service.exception.InventarioTurnoException;
import eterea.core.service.kotlin.model.InventarioTurno;
import eterea.core.service.service.InventarioTurnoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping({"/api/core/inventarioturno", "/inventarioturno"})
public class InventarioTurnoController {

    private final InventarioTurnoService service;

    public InventarioTurnoController(InventarioTurnoService service) {
        this.service = service;
    }

    @GetMapping("/")
    public ResponseEntity<List<InventarioTurno>> findAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{inventarioTurnoId}")
    public ResponseEntity<InventarioTurno> findByInventarioTurnoId(@PathVariable Integer inventarioTurnoId) {
        try {
            return new ResponseEntity<>(service.findByInventarioTurnoId(inventarioTurnoId), HttpStatus.OK);
        } catch (InventarioTurnoException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/last")
    public ResponseEntity<InventarioTurno> findLast() {
        try {
            return new ResponseEntity<>(service.findLast(), HttpStatus.OK);
        } catch (InventarioTurnoException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping("/")
    public ResponseEntity<InventarioTurno> create(@RequestBody InventarioTurno inventarioTurno) {
        return new ResponseEntity<>(service.create(inventarioTurno), HttpStatus.CREATED);
    }

    @PutMapping("/{inventarioTurnoId}")
    public ResponseEntity<InventarioTurno> update(@PathVariable Integer inventarioTurnoId, @RequestBody InventarioTurno inventarioTurno) {
        try {
            return new ResponseEntity<>(service.update(inventarioTurnoId, inventarioTurno), HttpStatus.OK);
        } catch (InventarioTurnoException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

}
