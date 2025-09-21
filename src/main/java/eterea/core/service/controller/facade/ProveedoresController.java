package eterea.core.service.controller.facade;

import eterea.core.service.kotlin.model.ProveedorMovimiento;
import eterea.core.service.service.facade.ProveedoresService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping({"/api/core/proveedores", "/proveedores"})
@RequiredArgsConstructor
public class ProveedoresController {

    private final ProveedoresService service;

    @GetMapping("/proveedor/comprobantes/sin/rendicion/{proveedorId}")
    public ResponseEntity<List<ProveedorMovimiento>> findAllByProveedorIdSinRendicion(@PathVariable Long proveedorId) {
        return new ResponseEntity<>(service.findAllByProveedorIdSinRendicion(proveedorId), HttpStatus.OK);
    }

}
