package eterea.core.service.hexagonal.contable.cuentamovimiento.infrastructure.web.controller;

import eterea.core.service.hexagonal.contable.cuentamovimiento.application.exception.CuentaMovimientoException;
import eterea.core.service.hexagonal.contable.cuentamovimiento.application.service.CuentaMovimientoService;
import eterea.core.service.hexagonal.contable.cuentamovimiento.domain.model.CuentaMovimiento;
import eterea.core.service.hexagonal.contable.cuentamovimiento.infrastructure.web.dto.CuentaMovimientoResponse;
import eterea.core.service.hexagonal.contable.cuentamovimiento.infrastructure.web.mapper.CuentaMovimientoDtoMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping({"/api/core/cuentaMovimiento", "/cuentaMovimiento"})
public class CuentaMovimientoController {

    private final CuentaMovimientoService service;
    private final CuentaMovimientoDtoMapper mapper;

    public CuentaMovimientoController(CuentaMovimientoService service, CuentaMovimientoDtoMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping("/{cuentaMovimientoId}")
    public ResponseEntity<CuentaMovimientoResponse> findByCuentaMovimientoId(@PathVariable Long cuentaMovimientoId) {
        try {
            CuentaMovimiento domain = service.findByCuentaMovimientoId(cuentaMovimientoId);
            return new ResponseEntity<>(mapper.toResponse(domain), HttpStatus.OK);
        } catch (CuentaMovimientoException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

}
