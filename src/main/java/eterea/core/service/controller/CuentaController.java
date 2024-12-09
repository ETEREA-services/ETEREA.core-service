package eterea.core.service.controller;

import eterea.core.service.kotlin.model.Cuenta;
import eterea.core.service.service.CuentaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/cuenta", "/api/core/cuenta"})
public class CuentaController {

    private final CuentaService service;

    public CuentaController(CuentaService service) {
        this.service = service;
    }

    @GetMapping("/{numeroCuenta}")
    public ResponseEntity<Cuenta> findByNumeroCuenta(@PathVariable Long numeroCuenta) {
        return ResponseEntity.ok(service.findByNumeroCuenta(numeroCuenta));
    }

}
