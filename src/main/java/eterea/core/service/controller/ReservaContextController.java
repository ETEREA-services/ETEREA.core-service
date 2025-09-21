package eterea.core.service.controller;

import eterea.core.service.model.ReservaContext;
import eterea.core.service.service.ReservaContextService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping({"/api/core/reservacontext", "/reservacontext"})
@RequiredArgsConstructor
public class ReservaContextController {

    private final ReservaContextService service;

    @GetMapping("/facturacion/pendiente")
    public ResponseEntity<List<ReservaContext>> findAllByFacturacionPendiente() {
        return ResponseEntity.ok(service.findAllByFacturacionPendiente());
    }

}
