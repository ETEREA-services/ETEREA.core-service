package eterea.core.api.rest.controller.facade;

import eterea.core.api.rest.kotlin.model.dto.FacturacionDto;
import eterea.core.api.rest.service.extern.FacturacionElectronicaService;
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

    @PostMapping("/afip/make/{negocioId}")
    public ResponseEntity<FacturacionDto> makeFactura(@RequestBody FacturacionDto facturacionDto, @PathVariable Integer negocioId) {
        return new ResponseEntity<>(facturacionElectronicaService.makeFactura(facturacionDto, negocioId), HttpStatus.OK);
    }

}
