package eterea.core.service.controller.facade;

import eterea.core.service.model.Track;
import eterea.core.service.service.TrackService;
import eterea.core.service.service.facade.MakeFacturaProgramaDiaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"/api/core/make-factura-programa-dia", "/make-factura-programa-dia"})
public class MakeFacturaProgramaDiaController {

    private final MakeFacturaProgramaDiaService service;
    private final TrackService trackService;

    public MakeFacturaProgramaDiaController(MakeFacturaProgramaDiaService service, TrackService trackService) {
        this.service = service;
        this.trackService = trackService;
    }

    @GetMapping("/factura/{reservaId}/{comprobanteId}")
    public ResponseEntity<Boolean> facturaReserva(@PathVariable Long reservaId, @PathVariable Integer comprobanteId, @RequestHeader(name = "X-Request-ID", required = false) String trackUuid) {
        Track track;
        if (trackUuid != null) {
            track = trackService.findByUuid(trackUuid);
        } else {
            track = trackService.startTracking("factura-reserva-" + reservaId);
        }
        return ResponseEntity.ok(service.facturaReserva(reservaId, comprobanteId, track));
    }

}
