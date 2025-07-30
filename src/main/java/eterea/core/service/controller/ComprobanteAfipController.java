package eterea.core.service.controller;

import eterea.core.service.kotlin.model.ComprobanteAfip;
import eterea.core.service.service.ComprobanteAfipService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/api/core/comprobanteAfip", "/comprobanteAfip"})
public class ComprobanteAfipController {

    private final ComprobanteAfipService service;

    public ComprobanteAfipController(ComprobanteAfipService service) {
        this.service = service;
    }

    @GetMapping("/{comprobanteAfipId}")
    public ResponseEntity<ComprobanteAfip> findByComprobanteAfipId(@PathVariable Integer comprobanteAfipId) {
        return ResponseEntity.ok(service.findByComprobanteAfipId(comprobanteAfipId));
    }

}
