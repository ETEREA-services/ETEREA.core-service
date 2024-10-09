package eterea.core.service.controller;

import eterea.core.service.kotlin.model.FacturaRendicionDetalle;
import eterea.core.service.service.FacturaRendicionDetalleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping({"/api/core/facturarendidadetalle", "/facturarendidadetalle"})
public class FacturaRendicionDetalleController {

    private final FacturaRendicionDetalleService service;

    public FacturaRendicionDetalleController(FacturaRendicionDetalleService service) {
        this.service = service;
    }

    @GetMapping("/proveedor/{proveedorId}")
    public ResponseEntity<List<FacturaRendicionDetalle>> findAllByProveedorId(@PathVariable Long proveedorId) {
        return new ResponseEntity<>(service.findAllByProveedorId(proveedorId), HttpStatus.OK);
    }

}
