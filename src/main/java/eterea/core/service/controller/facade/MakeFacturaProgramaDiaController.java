package eterea.core.service.controller.facade;

import eterea.core.service.model.Track;
import eterea.core.service.service.facade.MakeFacturaProgramaDiaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"/api/core/make-factura-programa-dia", "/make-factura-programa-dia"})
public class MakeFacturaProgramaDiaController {

    private final MakeFacturaProgramaDiaService service;

    public MakeFacturaProgramaDiaController(MakeFacturaProgramaDiaService service) {
        this.service = service;
    }

    @PostMapping("/factura/{reservaId}/{comprobanteId}")
    public ResponseEntity<Boolean> facturaReserva(@PathVariable Long reservaId, @PathVariable Integer comprobanteId, @RequestBody Track track) {
        return ResponseEntity.ok(service.facturaReserva(reservaId, comprobanteId, track));
    }

}
