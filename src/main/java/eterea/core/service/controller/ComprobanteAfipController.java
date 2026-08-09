package eterea.core.service.controller;

import eterea.core.service.kotlin.model.ComprobanteAfip;
import eterea.core.service.service.ComprobanteAfipService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/api/core/comprobanteAfip", "/comprobanteAfip"})
@RequiredArgsConstructor
public class ComprobanteAfipController {

    private final ComprobanteAfipService service;

    @GetMapping("/{comprobanteAfipId}")
    public ResponseEntity<ComprobanteAfip> findByComprobanteAfipId(@PathVariable Integer comprobanteAfipId) {
        return ResponseEntity.ok(service.findByComprobanteAfipId(comprobanteAfipId));
    }

}
