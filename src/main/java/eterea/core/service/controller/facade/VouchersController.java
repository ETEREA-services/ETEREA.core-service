package eterea.core.service.controller.facade;

import eterea.core.service.kotlin.model.dto.ProgramaDiaDto;
import eterea.core.service.model.Track;
import eterea.core.service.service.TrackService;
import eterea.core.service.service.facade.VouchersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"/api/core/vouchers", "/vouchers"})
@RequiredArgsConstructor
public class VouchersController {

    private final VouchersService service;
    private final TrackService trackService;

    @GetMapping("/import/web/one/{orderNumberId}")
    public ResponseEntity<ProgramaDiaDto> importOneFromWeb(@PathVariable Long orderNumberId, @RequestHeader(name = "X-Request-ID", required = false) String trackUuid) {
        Track track;
        if (trackUuid != null) {
            track = trackService.findByUuid(trackUuid);
        } else {
            track = trackService.startTracking("import-one-from-web-" + orderNumberId);
        }
        return ResponseEntity.ok(service.importOneFromWeb(orderNumberId, track));
    }

}

