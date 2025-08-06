package eterea.core.service.controller.facade;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eterea.core.service.kotlin.model.ClienteMovimiento;
import eterea.core.service.model.dto.DatoUnaFacturaDto;
import eterea.core.service.service.facade.FacturacionService;

@RestController
@RequestMapping({"/api/core/facturacion", "/facturacion"})
public class FacturacionController {

    private final FacturacionService service;

    public FacturacionController(FacturacionService service) {
        this.service = service;
    }

    @PostMapping("/registrarTransaccionFacturaFaltante")
    public ResponseEntity<ClienteMovimiento> registrarTransaccionFacturaFaltante(@RequestBody DatoUnaFacturaDto datoUnaFactura) {
        return ResponseEntity.ok(service.registraTransaccionFacturaFaltante(datoUnaFactura.getClienteMovimiento(), datoUnaFactura.getArticuloMovimiento()));
    }
}
