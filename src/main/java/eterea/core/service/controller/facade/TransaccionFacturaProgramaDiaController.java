package eterea.core.service.controller.facade;

import eterea.core.service.kotlin.model.dto.FacturacionDto;
import eterea.core.service.service.facade.TransaccionFacturaProgramaDiaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/core/transaccion-factura-programa-dia")
public class TransaccionFacturaProgramaDiaController {

    private final TransaccionFacturaProgramaDiaService service;

    public TransaccionFacturaProgramaDiaController(TransaccionFacturaProgramaDiaService service) {
        this.service = service;
    }

    @PostMapping("/registro/{orderNumberId}/dry-run/{dryRun}")
    public ResponseEntity<Void> registroTransaccionFacturaProgramaDia(@PathVariable Long orderNumberId, @PathVariable Boolean dryRun, @RequestBody FacturacionDto facturacionDto) {
        service.registroTransaccionFacturaProgramaDia(orderNumberId, facturacionDto, dryRun);
        return ResponseEntity.ok().build();
    }
}
