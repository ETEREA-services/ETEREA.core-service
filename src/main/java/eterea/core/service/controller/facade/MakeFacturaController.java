package eterea.core.service.controller.facade;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eterea.core.service.kotlin.model.dto.FacturacionDto;
import eterea.core.service.service.extern.FacturacionElectronicaService;
import eterea.core.service.service.facade.MakeFacturaService;

@RestController
@RequestMapping({ "/api/core/makeFactura", "/makeFactura" })
public class MakeFacturaController {

    private final FacturacionElectronicaService facturacionElectronicaService;
    private final MakeFacturaService makeFacturaService;

    public MakeFacturaController(FacturacionElectronicaService facturacionElectronicaService,
            MakeFacturaService makeFacturaService) {
        this.facturacionElectronicaService = facturacionElectronicaService;
        this.makeFacturaService = makeFacturaService;
    }

    @PostMapping("/afip/make")
    public ResponseEntity<FacturacionDto> makeFactura(@RequestBody FacturacionDto facturacionDto) {
        return new ResponseEntity<>(facturacionElectronicaService.makeFactura(facturacionDto), HttpStatus.OK);
    }

    @PostMapping("/reserva/{reservaId}/comprobante/{comprobanteId}/valor/{valorId}")
    public ResponseEntity<Long> makeFactura(@PathVariable Long reservaId, @PathVariable Integer comprobanteId,
            @PathVariable Integer valorId) {
        return new ResponseEntity<>(makeFacturaService.facturaReserva(reservaId, comprobanteId, valorId),
                HttpStatus.OK);
    }

}
