package eterea.core.service.controller.facade;

import eterea.core.service.model.dto.FacturacionDto;
import eterea.core.service.service.extern.FacturacionElectronicaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"/api/core/makeFactura", "/makeFactura"})
public class MakeFacturaController {

    private final FacturacionElectronicaService facturacionElectronicaService;

    public MakeFacturaController(FacturacionElectronicaService facturacionElectronicaService) {
        this.facturacionElectronicaService = facturacionElectronicaService;
    }

    @PostMapping("/afip/make")
    public ResponseEntity<FacturacionDto> makeFactura(@RequestBody FacturacionDto facturacionDto) {
        return new ResponseEntity<>(facturacionElectronicaService.makeFactura(facturacionDto), HttpStatus.OK);
    }

}
