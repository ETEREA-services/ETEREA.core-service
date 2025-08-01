package eterea.core.service.controller.facade;

import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import eterea.core.service.kotlin.model.ClienteMovimiento;
import eterea.core.service.model.dto.DatoUnaFacturaDto;
import eterea.core.service.service.facade.ContabilidadService;
import eterea.core.service.service.facade.FacturacionService;

@RestController
@RequestMapping({"/api/core/facturacion", "/facturacion"})
public class FacturacionController {

    private final FacturacionService service;
    private final ContabilidadService contabilidadService;

    public FacturacionController(FacturacionService service, ContabilidadService contabilidadService) {
        this.service = service;
        this.contabilidadService = contabilidadService;
    }

    @PostMapping("/registrarTransaccionFacturaFaltante")
    public ResponseEntity<ClienteMovimiento> registrarTransaccionFacturaFaltante(@RequestBody DatoUnaFacturaDto datoUnaFactura) {
        return ResponseEntity.ok(service.registraTransaccionFacturaFaltante(datoUnaFactura.getClienteMovimiento(), datoUnaFactura.getArticuloMovimiento()));
    }

    @GetMapping("/checkOrphanFacturas")
    public ResponseEntity<List<ClienteMovimiento>> checkOrphanFacturas(@RequestParam String desde, @RequestParam String hasta, @RequestParam Integer comprobanteId) {
        OffsetDateTime desdeDate = OffsetDateTime.parse(desde);
        OffsetDateTime hastaDate = OffsetDateTime.parse(hasta);
        return ResponseEntity.ok(contabilidadService.checkOrphanFacturas(desdeDate, hastaDate, comprobanteId));
    }

    @PostMapping("/fixOrphanFacturas")
    public ResponseEntity<Void> fixOrphanFacturas(@RequestParam String desde, @RequestParam String hasta, @RequestParam Integer comprobanteId) {
        OffsetDateTime desdeDate = OffsetDateTime.parse(desde);
        OffsetDateTime hastaDate = OffsetDateTime.parse(hasta);
        contabilidadService.fixOrphanFacturas(contabilidadService.checkOrphanFacturas(desdeDate, hastaDate, comprobanteId), comprobanteId);
        return ResponseEntity.ok().build();
    }

}
