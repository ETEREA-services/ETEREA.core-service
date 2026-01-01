package eterea.core.service.hexagonal.facturacion.arca.nacional.infrastructure.web.controller;

import eterea.core.service.hexagonal.facturacion.arca.nacional.application.service.FacturacionElectronicaService;
import eterea.core.service.hexagonal.facturacion.arca.nacional.infrastructure.web.dto.FacturacionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"/api/core/makeFactura", "/makeFactura"})
@RequiredArgsConstructor
public class MakeFacturaController {

    private final FacturacionElectronicaService facturacionElectronicaService;

    @PostMapping("/afip/make")
    public ResponseEntity<FacturacionDto> makeFactura(@RequestBody FacturacionDto facturacionDto) {
        return ResponseEntity.ok(facturacionElectronicaService.makeFactura(facturacionDto));
    }

}
