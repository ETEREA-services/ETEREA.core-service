package eterea.core.service.controller.facade;

import eterea.core.service.kotlin.model.dto.ProgramaDiaDto;
import eterea.core.service.model.Track;
import eterea.core.service.service.facade.VouchersService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"/api/core/vouchers", "/vouchers"})
public class VouchersController {

    private final VouchersService service;

    public VouchersController(VouchersService service) {
        this.service = service;
    }

    @PostMapping("/import/web/one/{orderNumberId}")
    public ResponseEntity<ProgramaDiaDto> importOneFromWeb(@PathVariable Long orderNumberId, @RequestBody Track track) {
        return ResponseEntity.ok(service.importOneFromWeb(orderNumberId, track));
    }

}

