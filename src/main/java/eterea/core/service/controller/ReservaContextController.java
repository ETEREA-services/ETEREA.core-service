package eterea.core.service.controller;

import eterea.core.service.model.ReservaContext;
import eterea.core.service.service.ReservaContextService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping({"/api/core/reservacontext", "/reservacontext"})
public class ReservaContextController {

    private final ReservaContextService service;

    public ReservaContextController(ReservaContextService service) {
        this.service = service;
    }

    @GetMapping("/facturacion/pendiente")
    public ResponseEntity<List<ReservaContext>> findAllByFacturacionPendiente() {
        return ResponseEntity.ok(service.findAllByFacturacionPendiente());
    }

}
