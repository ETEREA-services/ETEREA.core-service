package eterea.core.service.controller;

import eterea.core.service.kotlin.model.ProveedorMovimiento;
import eterea.core.service.service.ProveedorMovimientoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping({"/api/core/proveedormovimiento", "/proveedormovimiento"})
public class ProveedorMovimientoController {

    private final ProveedorMovimientoService service;

    public ProveedorMovimientoController(ProveedorMovimientoService service) {
        this.service = service;
    }

    @GetMapping("/proveedor/{proveedorId}")
    public ResponseEntity<List<ProveedorMovimiento>> findAllByProveedorId(@PathVariable Long proveedorId) {
        return new ResponseEntity<>(service.findAllByProveedorId(proveedorId), HttpStatus.OK);
    }

}
