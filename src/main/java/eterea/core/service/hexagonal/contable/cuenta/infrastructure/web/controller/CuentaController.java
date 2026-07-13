package eterea.core.service.hexagonal.contable.cuenta.infrastructure.web.controller;

import eterea.core.service.hexagonal.contable.cuenta.application.service.CuentaService;
import eterea.core.service.hexagonal.contable.cuenta.domain.model.Cuenta;
import eterea.core.service.hexagonal.contable.cuenta.infrastructure.web.dto.CuentaResponse;
import eterea.core.service.hexagonal.contable.cuenta.infrastructure.web.mapper.CuentaDtoMapper;
import eterea.core.service.kotlin.exception.CuentaException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping({"/cuenta", "/api/core/cuenta"})
public class CuentaController {

    private final CuentaService service;
    private final CuentaDtoMapper mapper;

    public CuentaController(CuentaService service, CuentaDtoMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping("/{numeroCuenta}")
    public ResponseEntity<CuentaResponse> findByNumeroCuenta(@PathVariable Long numeroCuenta) {
        try {
            Cuenta domain = service.findByNumeroCuenta(numeroCuenta);
            return ResponseEntity.ok(mapper.toResponse(domain));
        } catch (CuentaException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

}
