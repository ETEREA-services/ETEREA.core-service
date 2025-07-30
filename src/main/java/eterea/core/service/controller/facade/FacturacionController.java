package eterea.core.service.controller.facade;

import eterea.core.service.kotlin.model.ClienteMovimiento;
import eterea.core.service.model.dto.DatoUnaFacturaDto;
import eterea.core.service.service.facade.FacturacionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/registro/from/factura/{clienteMovimientoId}/nota/credito/{comprobanteId}")
    public ResponseEntity<ClienteMovimiento> registroNotaCreditoFromFactura(
            @PathVariable Long clienteMovimientoId,
            @PathVariable Integer comprobanteId
    ) {
        return ResponseEntity.ok(service.registroNotaCreditoFromFactura(clienteMovimientoId, comprobanteId));
    }

}
